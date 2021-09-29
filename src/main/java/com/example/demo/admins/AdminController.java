package com.example.demo.admins;


//import com.example.demo.admin_role.AdminRolePair;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.roles.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(path="api/v1/")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path="/admins")
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok().body(adminService.getAdmins());
    }

    @PostMapping(path="/admins/save")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/admins/save").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveAdmin(admin));
    }

    @PostMapping(path="/roles/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/roles/save").toUriString());

        return ResponseEntity.created(uri).body(adminService.saveRole(role));
    }

    @PostMapping(path = "/roles/addtouser")
    public ResponseEntity<?> addRoleToAdmin(
            @RequestParam(required = false)String email,
            @RequestParam(required = false)String role
    ){
        adminService.addRoleToAdmin(email, role);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path="/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            try{

                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();

                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String username = decodedJWT.getSubject();

                Admin user = adminService.getAdmin(username);


                String  accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withClaim("roles",user.getRoles().stream()
                                .map(Role::getName).collect(Collectors.toList()))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap();

                tokens.put("access_token",accessToken);
                tokens.put("refresh_token",refreshToken);

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch (Exception exception){
                System.out.println(exception.getMessage());

                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                HashMap<String,String> errors = new HashMap<String,String>();
                errors.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(),errors);
            }
        }
        else{
            throw new RuntimeException("The refresh token is missing");
        }
    }

}

