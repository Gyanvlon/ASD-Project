package MRTS.controller;

import MRTS.DTO.LogInDto;
import MRTS.DTO.UserDto;
import MRTS.DTO.UserResponse;
import MRTS.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "User Service", description = "Operations related to user management")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    @Operation(summary = "Register User", description = "Register a new user")
    public ResponseEntity<UserResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }
    @PostMapping("/login")
    @Operation(summary = "Log in User", description = "Log in a user")
    public ResponseEntity<UserResponse> logInUser( @RequestBody LogInDto userDto) {
        return ResponseEntity.ok(userService.logInUser(userDto));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get a User by username", description = "Get a single User by their username.")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a User by ID", description = "Get a single User by their ID.")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.findByUserId(id), HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    @Operation(summary = "Update a User by ID", description = "Update a single User by their ID.")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable UUID userId, @Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.updateUserById(userId, user), HttpStatus.OK);
    }
    @PatchMapping("/{userId}")
    @Operation(summary = "Patch a User by ID", description = "Patch a single User by their ID.")
    public ResponseEntity<UserResponse> patchUserById(@PathVariable UUID userId, @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.patchUserById(userId, user), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a User by ID", description = "Delete a single User by their ID.")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User with id " + userId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Users", description = "Return all Users.")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
