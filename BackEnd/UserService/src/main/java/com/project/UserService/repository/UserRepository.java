package com.project.UserService.repository;

import com.project.UserService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByuserEmail(String userEmail);
}