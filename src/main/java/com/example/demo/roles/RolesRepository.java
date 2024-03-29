package com.example.demo.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

 //   @Query("SELECT r FROM Role r WHERE r.name=?1")
    Optional<Role> findRoleByName(String name);
}
