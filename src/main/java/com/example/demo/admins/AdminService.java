package com.example.demo.admins;

import com.example.demo.roles.Role;
import com.example.demo.roles.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service @Transactional
public class AdminService implements IAdminService, UserDetailsService {

    private final RolesRepository rolesRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(RolesRepository rolesRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(s).orElseThrow(
                ()-> new UsernameNotFoundException("There is no Admin with this email")
        );

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
/*
        admin.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });*/

        return new User(admin.getEmail(),admin.getPassword(),authorities);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        System.out.println("Saving Admin");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Role saveRole(Role role) {
        System.out.println("Saving Role");
        return rolesRepository.save(role);
    }

    @Override
    public void addRoleToAdmin(String userEmail, String roleName) {
        Admin admin = adminRepository.findByEmail(userEmail).orElseThrow(
                ()-> new UsernameNotFoundException("There is no Admin with this email")
        );

        Role role = rolesRepository.findRoleByName(roleName).orElseThrow(
                ()-> new IllegalStateException("There is no role with this name")
        );

        System.out.println("Add Role to Admin");
  //      admin.getRoles().add(role);
    }

    @Override
    public Admin getAdmin(String userEmail) {

        return adminRepository.findByEmail(userEmail).orElseThrow(
                ()-> new UsernameNotFoundException("There is no user with this email")
        );
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

}
