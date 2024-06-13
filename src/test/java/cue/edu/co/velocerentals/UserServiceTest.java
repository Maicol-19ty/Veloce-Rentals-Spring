package cue.edu.co.velocerentals;

import cue.edu.co.velocerentals.mapping.dto.UserDTO;
import cue.edu.co.velocerentals.domain.models.User;
import cue.edu.co.velocerentals.repository.UserRepository;
import cue.edu.co.velocerentals.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO usersDTo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1)
                .username("testuser")
                .password("password")
                .email("testuser@example.com")
                .fullName("Test User")
                .createdAt(Instant.now())
                .lastLogin(Instant.now())
                .build();

        usersDTo = new UserDTO(
                "testuser", "password", "testuser@example.com",
                "Test User", Instant.now(), Instant.now()
        );
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUser = userService.createUser(usersDTo);

        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.username());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        UserDTO foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.username());
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateUser(1, usersDTo);

        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.username());
        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_NotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        UserDTO updatedUser = userService.updateUser(1, usersDTo);

        assertNull(updatedUser);
        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(anyInt());

        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testLogin_Success() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDTO loggedInUser = userService.login("testuser", "password");

        assertNotNull(loggedInUser);
        assertEquals(user.getUsername(), loggedInUser.username());
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void testLogin_Failure() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDTO loggedInUser = userService.login("testuser", "wrongpassword");

        assertNull(loggedInUser);
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(user.getUsername(), users.get(0).username());
        verify(userRepository, times(1)).findAll();
    }
}
