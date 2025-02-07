package com.project.UserAuthentication.service;

import com.project.UserAuthentication.domain.User;
import com.project.UserAuthentication.exception.InvalidCredentialException;
import com.project.UserAuthentication.exception.UserAlreadyExistsException;
import com.project.UserAuthentication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService  {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        //save the user in the db
        System.out.println("in service");
        if(userRepository.findById(user.getUserEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        System.out.println(user);
        return userRepository.save(user);
    }




    @Override
public User findByUserEmailAndPassword(String userEmail, String password) throws InvalidCredentialException {
    // Validate for wrong credentials
        User user =  userRepository.findByUserEmailAndPassword(userEmail , password);
        if(user == null){
            throw new InvalidCredentialException();
        }
        return user;

    }
}

