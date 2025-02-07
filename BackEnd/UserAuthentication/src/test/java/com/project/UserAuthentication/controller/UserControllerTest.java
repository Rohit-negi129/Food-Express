package com.project.UserAuthentication.controller;

import com.project.UserAuthentication.domain.User;
import com.project.UserAuthentication.exception.UserAlreadyExistsException;
import com.project.UserAuthentication.exception.InvalidCredentialException;
import com.project.UserAuthentication.security.SecurityTokenGenerator;
import com.project.UserAuthentication.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private IUserService iUserService;

    @Mock
    private SecurityTokenGenerator securityTokenGenerator;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotated components
    }

    @Test
    public void givenUserToSaveReturnUserSuccess() throws UserAlreadyExistsException {
        // Arrange
        User user = new User("email@example.com", "password123", "USER");

        Mockito.when(iUserService.saveUser(Mockito.any())).thenReturn(user);

        // Act
        ResponseEntity<?> result = userController.saveUser(user);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(user, result.getBody());
    }

    @Test
    public void givenUserToSaveReturnUserFailure() throws UserAlreadyExistsException {
        // Arrange
        User user = new User("test@example.com", "password", "USER");
        Mockito.when(iUserService.saveUser(Mockito.any())).thenThrow(UserAlreadyExistsException.class);

        // Act & Assert
        ResponseEntity<?> result = userController.saveUser(user);

        // Additional Assert
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    public void givenUserLoginSuccessReturnToken() throws InvalidCredentialException {
        // Arrange
        User user = new User("email@example.com", "password", "USER");
        String expectedToken = "mockedToken";
        Mockito.when(iUserService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword())).thenReturn(user);
        Mockito.when(securityTokenGenerator.createToken(user)).thenReturn(expectedToken);

        // Act
        ResponseEntity<?> result = userController.login(user);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedToken, result.getBody());
    }

    @Test
    public void givenUserLoginFailureReturnInvalidCredentialException() {
        // Arrange
        User user = new User("email@example.com", "password", "USER");
        try {
            Mockito.when(iUserService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword())).thenReturn(null);
        } catch (InvalidCredentialException e) {
            throw new RuntimeException(e);
        }

        // Act & Assert
        assertThrows(InvalidCredentialException.class, () -> userController.login(user));
    }
}
