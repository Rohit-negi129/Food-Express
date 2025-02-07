//package com.project.UserService.controller;
//
//
//import com.project.UserService.domain.User;
//import com.project.UserService.exception.UserAlreadyExistsException;
//import com.project.UserService.service.IUserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest {
//
//    @Mock
//    private IUserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Test
//    public void testRegisterUserSuccess() throws UserAlreadyExistsException {
//        // Given
//        User user = new User("test@example.com", "password", "user", null);
//
//        // Ensure userService is properly initialized
//        when(userService.registerUser(any(User.class))).thenReturn(user);
//
//        // When
//        ResponseEntity<?> responseEntity = userController.registerUser(user);
//
//        // Then
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals(user, responseEntity.getBody());
//        verify(userService, times(1)).registerUser(eq(user));
//    }
//
//    @Test
//    public void testRegisterUserFailure() throws UserAlreadyExistsException {
//        // Given
//        User existingUser = new User("existing@example.com", "password", "user", null);
//
//        // Ensure userService is properly initialized
//        doThrow(new UserAlreadyExistsException()).when(userService).registerUser(eq(existingUser));
//
//        // When
//        ResponseEntity<?> responseEntity = userController.registerUser(existingUser);
//
//        // Then
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//        verify(userService, times(1)).registerUser(eq(existingUser));
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        // Given
//        List<User> userList = Collections.singletonList(new User("test@example.com", "password", "user", null));
//
//        // Ensure userService is properly initialized
//        when(userService.getAllUsers()).thenReturn(userList);
//
//        // When
//        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(userList, responseEntity.getBody());
//        verify(userService, times(1)).getAllUsers();
//    }
//}
