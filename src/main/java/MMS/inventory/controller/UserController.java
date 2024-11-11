package MMS.inventory.controller;

import MMS.inventory.DTO.LogInDto;
import MMS.inventory.DTO.UserDto;
import MMS.inventory.DTO.UserResponse;
import MMS.inventory.domain.User;
import MMS.inventory.security.JwtService;
import MMS.inventory.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "User Service", description = "Operations related to user management")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    @Operation(summary = "Register User", description = "Register a new user")
    public ResponseEntity<UserResponse> register(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return ResponseEntity.ok(userService.createUser(userDto));
    }
    @PostMapping("/login")
    @Operation(summary = "Log in User", description = "Log in a user")
    public ResponseEntity<UserResponse> logInUser( @RequestBody LogInDto userDto) {
        return ResponseEntity.ok(userService.logInUser(userDto));
    }
//    @PostMapping
//    @Operation(summary = "Register User", description = "Register a new user")
//    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
//        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//    }
//    @GetMapping("/{userName}")
//    public ResponseEntity<UserDto> getUser(@PathVariable String userName) {
//        return new ResponseEntity<>(userService.findByUsername(userName), HttpStatus.OK);
//    }
    @PutMapping("/{userId}")
    @Operation(summary = "Update a User by ID", description = "Update a single User by their ID.")
    public ResponseEntity<UserDto> updateUserById(@PathVariable UUID userId, @Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.updateUserById(userId, user), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a User by ID", description = "Delete a single User by their ID.")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User with id " + userId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Users", description = "Return all Users.")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
