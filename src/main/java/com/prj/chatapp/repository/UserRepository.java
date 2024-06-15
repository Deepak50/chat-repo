package com.prj.chatapp.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prj.chatapp.entity.Userr;


public interface UserRepository extends JpaRepository<Userr, String> {
    Optional<Userr> findByUserId(String userId);
    
    @Query(value = "select u.user_id, u.user_name from user u", nativeQuery = true)
    List<Object[]> getAllUsers();
}
