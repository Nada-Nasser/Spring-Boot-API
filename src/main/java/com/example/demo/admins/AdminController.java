package com.example.demo.admins;


//import com.example.demo.admin_role.AdminRolePair;
import com.example.demo.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

}

