//package com.project.UserService.repository;
//
//import com.project.UserService.domain.User;
//import com.project.UserService.exception.UserNotFoundException;
//import com.project.UserService.repository.UserRepository;
//import com.project.UserService.service.IUserService;
//import com.project.UserService.service.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class UserRepositoryTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    // Use the implementation of IUserService
//    @InjectMocks
//    private IUserService userService = new UserServiceImpl(userRepository);
//
//    @Test
//    public void testFindByUserEmailAndPassword_Success() throws UserNotFoundException {
//        // Given
//        User user = new User("test@example.com", "password", "user", null);
//        when(userRepository.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword())).thenReturn(Optional.of(user));
//
//        // When
//        User foundUser = userService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
//
//        // Then
//        assertEquals(user, foundUser, "User should be found");
//    }
//
//    @Test
//    public void testFindByUserEmailAndPassword_Failure() {
//        // Given
//        User user = new User("test@example.com", "password", "user", null);
//        when(userRepository.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword())).thenReturn(Optional.empty());
//
//        // When and Then
//        assertThrows(UserNotFoundException.class, () -> userService.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword()), "UserNotFoundException should be thrown");
//    }
//}
