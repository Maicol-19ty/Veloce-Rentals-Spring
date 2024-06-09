package cue.edu.co.velocerentals;

import cue.edu.co.velocerentals.mapping.DTO.UserDTo;
import cue.edu.co.velocerentals.models.User;
import cue.edu.co.velocerentals.repository.UserRepository;
import cue.edu.co.velocerentals.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTo usersDTo;

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

        usersDTo = new UserDTo(
                1, "testuser", "password", "testuser@example.com",
                "Test User", Instant.now(), Instant.now()
        );
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTo createdUser = userService.createUser(usersDTo);

        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.username());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        UserDTo foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.username());
        verify(userRepository, times(1)).findById(anyInt());
    }
}
