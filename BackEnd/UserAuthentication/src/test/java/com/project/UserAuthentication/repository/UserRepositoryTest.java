package com.project.UserAuthentication.repository;

import com.project.UserAuthentication.domain.User;
import com.project.UserAuthentication.exception.InvalidCredentialException;
import com.project.UserAuthentication.exception.UserAlreadyExistsException;
import com.project.UserAuthentication.service.UserServiceImpl;
import org.apache.http.auth.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {
        // Arrange
        User user = new User("test@example.com", "password", "USER");

        // Mocking the save method of the repository
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        // Add more assertions based on your requirements
    }

    @Test
    public void testLoginUserSuccess() throws InvalidCredentialException {
        // Arrange
        String userEmail = "test@example.com";
        String password = "password";
        User user = new User(userEmail, password, "USER");

        // Mocking the findByUserEmailAndPassword method of the repository
        when(userRepository.findByUserEmailAndPassword(userEmail, password)).thenReturn(user);

        // Act
        User loggedInUser = userService.findByUserEmailAndPassword(userEmail, password);

        // Assert
        verify(userRepository, times(1)).findByUserEmailAndPassword(userEmail, password);
        // Add more assertions based on your requirements
    }
}
