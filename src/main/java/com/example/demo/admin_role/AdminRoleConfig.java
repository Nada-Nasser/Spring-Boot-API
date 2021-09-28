package com.example.demo.admin_role;

import com.example.demo.admins.Admin;
import com.example.demo.admins.AdminService;
import com.example.demo.roles.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

public class AdminRoleConfig {
/*
    @Bean
    CommandLineRunner run(AdminService adminService){
        return args -> {
            adminService.saveRole(new Role(null,"ROLL_USER"));
            adminService.saveRole(new Role(null,"ROLL_MANAGER"));
            adminService.saveRole(new Role(null,"ROLL_ADMIN"));
            adminService.saveRole(new Role(null,"ROLL_SUPER_ADMIN"));

            adminService.saveAdmin(new Admin(null,"john","john@gmail.com","123456",new ArrayList<>()));
            adminService.saveAdmin(new Admin(null,"Sam","Sam@gmail.com","987654",new ArrayList<>()));
            adminService.saveAdmin(new Admin(null,"Sarah","Sarah@gmail.com","123456",new ArrayList<>()));
            adminService.saveAdmin(new Admin(null,"Ahmed","Ahmed@gmail.com","000000",new ArrayList<>()));

            adminService.addRoleToAdmin("john@gmail.com","ROLL_USER");
            adminService.addRoleToAdmin("john@gmail.com","ROLL_ADMIN");

            adminService.addRoleToAdmin("Sarah@gmail.com","ROLL_USER");
            adminService.addRoleToAdmin("Sarah@gmail.com","ROLL_SUPER_ADMIN");


            adminService.addRoleToAdmin("Ahmed@gmail.com","ROLL_USER");
            adminService.addRoleToAdmin("Ahmed@gmail.com","ROLL_SUPER_ADMIN");

            adminService.addRoleToAdmin("Sam@gmail.com","ROLL_ADMIN");
            adminService.addRoleToAdmin("Sam@gmail.com","ROLL_SUPER_ADMIN");
        };
    }
*/
}
