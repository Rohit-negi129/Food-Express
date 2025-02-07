package com.project.UserAuthentication.service;

import com.project.UserAuthentication.domain.User;
import com.project.UserAuthentication.exception.InvalidCredentialException;
import com.project.UserAuthentication.exception.UserAlreadyExistsException;


public interface IUserService {
     User saveUser(User user) throws UserAlreadyExistsException;

     User findByUserEmailAndPassword(String userEmail, String password) throws InvalidCredentialException ;



}

