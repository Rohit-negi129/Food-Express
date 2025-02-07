package com.project.UserAuthentication.repository;

import com.project.UserAuthentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserEmailAndPassword(String userEmail, String password);


}