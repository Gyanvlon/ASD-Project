package MRTS.controller;

import MRTS.DTO.LogInDto;
import MRTS.DTO.UserDto;
import MRTS.DTO.UserResponse;
import MRTS.domain.UserType;
import MRTS.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private UserDto userDto;
    private LogInDto logInDto;
    private UserResponse userResponse;
    @BeforeEach
    void setUp() {
        UUID uuidFixed = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
        userDto = new UserDto();
        userDto.setUserId(uuidFixed);
        userDto.setUserFullName("John Doe");
        userDto.setUserEmail("user@test.com");
        userDto.setUserPassword("passwordtest");
        userDto.setUserStatus(true);
        userDto.setRole(UserType.ADMIN);
        userResponse = new UserResponse();
        userResponse.setUserId(uuidFixed);
        userResponse.setUserFullName("John Doe");
        userResponse.setUserEmail("user@test.com");
        userResponse.setJwtToken("dfkladd03idkdfs_");
        userResponse.setUserStatus(true);
        userResponse.setRole(UserType.ADMIN);
        logInDto = new LogInDto();
        logInDto.setUserEmail("user@test.com");
        logInDto.setUserPassword("passwordtest");
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(userService);
    }

    @Test
    @Order(1)
    @DisplayName("Test Register User")
    void shouldRegister_WhenRegisterUser() {
        Mockito.when(userService.createUser(userDto)).thenReturn(userResponse).thenThrow(new IllegalArgumentException("invalid user data"));
        ResponseEntity<UserResponse> responseEntity = userController.register(userDto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().getRole());
        Mockito.verify(userService, Mockito.times(1)).createUser(userDto);
    }

    @Test
    @Order(2)
    @DisplayName("Test Log In User")
    void shouldLogInUser_WhenLogInUserIsValid() {
        Mockito.when(userService.logInUser(logInDto)).thenReturn(userResponse).thenThrow(new IllegalArgumentException("invalid user data"));
        ResponseEntity<UserResponse> responseEntity = userController.logInUser(logInDto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().getRole());
        Mockito.verify(userService, Mockito.times(1)).logInUser(logInDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"user@test.com"})
    @Order(3)
    @DisplayName("Test Get User by Email")
    void shouldGetUserByEmail_WhenValidEmail(String email) {
        Mockito.when(userService.findByEmail(email)).thenReturn(userResponse).thenThrow(new IllegalArgumentException("invalid user data"));
        ResponseEntity<UserResponse> responseEntity = userController.getUser(email);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().getRole());
        Mockito.verify(userService, Mockito.times(1)).findByEmail(email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-556642440000"})
    @Order(4)
    @DisplayName("Test Get User by ID")
    void shouldGetUserById_WhenValidId(UUID id) {
        Mockito.when(userService.findByUserId(id)).thenReturn(userResponse).thenThrow(new IllegalArgumentException("invalid user data"));
        ResponseEntity<UserResponse> responseEntity = userController.getUser(id);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().getRole());
        Mockito.verify(userService, Mockito.times(1)).findByUserId(id);
    }


    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-556642440000"})
    @Order(5)
    @DisplayName("Test Update User by ID")
    void shouldUpdateUserById_WhenValidId(UUID id) {
        Mockito.when(userService.updateUserById(id, userDto)).thenReturn(userResponse).thenThrow(new IllegalArgumentException("invalid user data"));
        userDto.setUserFullName("John Doe Updated");
        ResponseEntity<UserResponse> responseEntity = userController.updateUserById(id, userDto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().getRole());
        Mockito.verify(userService, Mockito.times(1)).updateUserById(id, userDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-556642440000"})
    @Order(6)
    @DisplayName("Test Patch User by ID")
    void shouldPatchUserById_WhenValidId(UUID id)  {
        Mockito.when(userService.patchUserById(id, userDto)).thenReturn(userResponse).thenThrow(new IllegalArgumentException("invalid user data"));
        userDto.setUserFullName("John Doe Patched");
        ResponseEntity<UserResponse> responseEntity = userController.patchUserById(id, userDto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().getRole());
        Mockito.verify(userService, Mockito.times(1)).patchUserById(id, userDto);
    }
    @Test
    @Order(7)
    @DisplayName("Test Delete User by ID")
    void shouldGetAllUser_WhenValidUserExist() {
        Mockito.when(userService.findAllUsers()).thenReturn(List.of(userResponse)).thenThrow(new IllegalArgumentException("invalid user data"));
        ResponseEntity<List<UserResponse>> responseEntity = userController.getAllUsers();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse.getUserFullName(), responseEntity.getBody().get(0).getUserFullName());
        assertEquals(userResponse.getUserEmail(), responseEntity.getBody().get(0).getUserEmail());
        assertEquals(userResponse.getJwtToken(), responseEntity.getBody().get(0).getJwtToken());
        assertEquals(userResponse.getUserStatus(), responseEntity.getBody().get(0).getUserStatus());
        assertEquals(userResponse.getRole(), responseEntity.getBody().get(0).getRole());
        Mockito.verify(userService, Mockito.times(1)).findAllUsers();
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-556642440000"})
    @Order(8)
    @DisplayName("Test Delete User by ID")
    void shouldDeleteUserById_WhenValidId(UUID id){
        Mockito.doNothing().when(userService).deleteUserById(id);
        ResponseEntity<String> responseEntity = userController.deleteUserById(id);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User with id " + id + " deleted successfully", responseEntity.getBody());
        Mockito.verify(userService, Mockito.times(1)).deleteUserById(id);
    }
}