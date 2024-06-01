package com.prj.chatapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prj.chatapp.entity.Userr;


public interface UserRepository extends JpaRepository<Userr, String> {
    Optional<Userr> findByUserId(String userId);
}
