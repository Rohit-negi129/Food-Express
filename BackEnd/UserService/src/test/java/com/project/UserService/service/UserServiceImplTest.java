//package com.project.UserService.service;
//
//import com.project.UserService.domain.User;
//import com.project.UserService.exception.UserAlreadyExistsException;
//import com.project.UserService.repository.UserRepository;
//import com.project.UserService.service.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//public class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//
//    @Test
//    public void testRegisterUser_Success() throws UserAlreadyExistsException {
//        // Arrange
//        User newUser = new User();
//        newUser.setUserEmail("test@example.com");
//        newUser.setPassword("password");
//
//        Mockito.when(userRepository.findById(newUser.getUserEmail())).thenReturn(Optional.empty());
//        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
//
//        // Act
//        User savedUser = userService.registerUser(newUser);
//
//        // Assert
//        assertEquals(newUser, savedUser);
//    }
//
//
//    @Test
//    public void testRegisterUser_UserAlreadyExists() {
//        // Arrange
//        User existingUser = new User("existing@example.com", "password");
//        Mockito.when(userRepository.findById(existingUser.getUserEmail())).thenReturn(java.util.Optional.of(existingUser));
//        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(existingUser));
//    }
//
//
//    @Test
//    public void testGetAllUsers() {
//        // Arrange
//        User user1 = new User("user1@example.com", "password1");
//        User user2 = new User("user2@example.com", "password2");
//        List<User> userList = Arrays.asList(user1, user2);
//        Mockito.when(userRepository.findAll()).thenReturn(userList);
//        List<User> retrievedUsers = userService.getAllUsers();
//        assertEquals(userList.size(), retrievedUsers.size());
//        assertEquals(userList.get(0), retrievedUsers.get(0));
//        assertEquals(userList.get(1), retrievedUsers.get(1));
//    }
//}
//
//
