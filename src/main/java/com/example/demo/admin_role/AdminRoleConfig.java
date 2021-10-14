package com.example.demo.admin_role;

import com.example.demo.admins.Admin;
import com.example.demo.admins.AdminService;
import com.example.demo.roles.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class AdminRoleConfig {
/*
    @Bean
    CommandLineRunner AdminRoleConfiguration(AdminService adminService){
        return args -> {
            adminService.saveRole(new Role("ROLL_USER"));
            adminService.saveRole(new Role("ROLL_MANAGER"));
            adminService.saveRole(new Role("ROLL_ADMIN"));
            adminService.saveRole(new Role("ROLL_SUPER_ADMIN"));

            adminService.saveAdmin(new Admin("john","john@gmail.com","123456",new ArrayList<Role>()));
            adminService.saveAdmin(new Admin("Sam","Sam@gmail.com","987654",new ArrayList<Role>()));
            adminService.saveAdmin(new Admin("Sarah","Sarah@gmail.com","123456",new ArrayList<Role>()));
            adminService.saveAdmin(new Admin("Ahmed","Ahmed@gmail.com","000000",new ArrayList<Role>()));


            adminService.addRoleToAdmin("john@gmail.com","ROLL_USER");
            adminService.addRoleToAdmin("john@gmail.com","ROLL_ADMIN");

            adminService.addRoleToAdmin("Sarah@gmail.com","ROLL_USER");
            adminService.addRoleToAdmin("Sarah@gmail.com","ROLL_SUPER_ADMIN");


            adminService.addRoleToAdmin("Ahmed@gmail.com","ROLL_USER");
            adminService.addRoleToAdmin("Ahmed@gmail.com","ROLL_SUPER_ADMIN");

            adminService.addRoleToAdmin("Sam@gmail.com","ROLL_ADMIN");
            adminService.addRoleToAdmin("Sam@gmail.com","ROLL_SUPER_ADMIN");
            adminService.addRoleToAdmin("Sam@gmail.com","ROLL_USER");

        };
    }
*/
}
