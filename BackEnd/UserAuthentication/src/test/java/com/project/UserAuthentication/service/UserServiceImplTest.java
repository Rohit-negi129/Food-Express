package com.project.UserAuthentication.service;

import com.project.UserAuthentication.domain.User;
import com.project.UserAuthentication.exception.InvalidCredentialException;
import com.project.UserAuthentication.exception.UserAlreadyExistsException;
import com.project.UserAuthentication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void givenUserToSaveReturnSaveUserSuccess() throws UserAlreadyExistsException {
        // Arrange
        User user = new User("test@example.com", "password", "USER");
        when(userRepository.findById(user.getUserEmail())).thenReturn(java.util.Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).findById(user.getUserEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void givenUserToSaveReturnSaveUserFailure() {
        // Arrange
        User user = new User("test@example.com", "password", "USER");
        when(userRepository.findById(user.getUserEmail())).thenReturn(java.util.Optional.of(user));

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(user));
        verify(userRepository, times(1)).findById(user.getUserEmail());
        verify(userRepository, never()).save(user);
    }

    @Test
    public void givenUserLoginSuccessReturnRetrievedUser() throws InvalidCredentialException {
        // Arrange
        User user = new User("test@example.com", "password", "USER");
        when(userRepository.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword())).thenReturn(user);

        // Act
        User retrievedUser = userService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());

        // Assert
        assertEquals(user, retrievedUser);
        verify(userRepository, times(1)).findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
    }

    @Test
    public void givenUserLoginFailureReturnInvalidCredentialsException() {
        // Arrange
        User user = new User("test@example.com", "password", "USER");
        when(userRepository.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword())).thenReturn(null);

        // Act & Assert
        assertThrows(InvalidCredentialException.class, () -> userService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword()));
        verify(userRepository, times(1)).findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
    }
}
