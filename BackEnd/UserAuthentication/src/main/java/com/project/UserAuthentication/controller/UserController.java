package com.project.UserAuthentication.controller;

import com.project.UserAuthentication.domain.User;
import com.project.UserAuthentication.exception.UserAlreadyExistsException;
import com.project.UserAuthentication.exception.InvalidCredentialException;
import com.project.UserAuthentication.security.SecurityTokenGenerator;
import com.project.UserAuthentication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private ResponseEntity responseEntity;
    private final IUserService iUserService;
    private final SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGenerator securityTokenGenerator) {
        this.iUserService = iUserService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


@PostMapping("/saveUser")
public ResponseEntity<?> saveUser(@RequestBody User user) {
    try {
        User user1=iUserService.saveUser(user);
       // System.out.println(user1);
        responseEntity = new ResponseEntity<>(user1, HttpStatus.CREATED);
    } catch (UserAlreadyExistsException e) {
        responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        // You can log the exception if needed
    }
    catch (Exception e) {
        responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws  InvalidCredentialException  {

        User retrievedUser = iUserService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
        if(retrievedUser==null)
        {
            throw new InvalidCredentialException();
        }
        String token = securityTokenGenerator.createToken(retrievedUser);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }


}

