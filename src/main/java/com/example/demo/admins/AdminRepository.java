package com.example.demo.admins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
  //  @Query("SELECT s FROM Admin s WHERE s.email=?1")
    Optional<Admin> findByEmail(String email);
}
