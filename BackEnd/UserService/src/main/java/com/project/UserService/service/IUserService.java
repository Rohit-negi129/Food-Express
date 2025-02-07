package com.project.UserService.service;

import com.project.UserService.domain.User;
import com.project.UserService.exception.UserAlreadyExistsException;
import com.project.UserService.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User registerUser(User user) throws UserAlreadyExistsException;

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String userEmail) throws UserNotFoundException;

}
