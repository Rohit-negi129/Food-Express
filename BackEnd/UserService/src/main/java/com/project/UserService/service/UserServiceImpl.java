package com.project.UserService.service;

import com.project.UserService.domain.User;
import com.project.UserService.exception.UserAlreadyExistsException;
import com.project.UserService.exception.UserNotFoundException;
import com.project.UserService.proxy.UserProxy;
import com.project.UserService.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;
    private UserProxy userProxy;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        // Register a new user
//        if(userRepository.findById(user.getUserEmail()).isPresent())
//        {
//            throw new UserAlreadyExistsException();
//        }
        User savedUser = userRepository.save(user);
        if(!(savedUser.getUserEmail().isEmpty())) {
            ResponseEntity responseEntity1=userProxy.saveUser(savedUser);
            System.out.println(responseEntity1.getBody());
        }
        return savedUser;
    }

    @Override
    public List<User> getAllUsers()  {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String userEmail) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByuserEmail(userEmail);
        if (userOptional.isPresent()) {
            return userOptional;
        } else {
            throw new UserNotFoundException();
        }
    }


}

