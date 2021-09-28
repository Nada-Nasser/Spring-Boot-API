package com.example.demo.admins;

import com.example.demo.roles.Role;

import java.util.List;


public interface IAdminService {

    Admin saveAdmin(Admin admin);
    Role saveRole(Role role);
    void addRoleToAdmin(String userEmail, String roleName);
    Admin getAdmin(String userEmail);
    List<Admin> getAdmins();
}
