package MMS.inventory.controller;

import MMS.inventory.DTO.UserDto;
import MMS.inventory.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "User Service", description = "Operations related to user management")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Register User", description = "Register a new user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
//    @GetMapping("/{userName}")
//    public ResponseEntity<UserDto> getUser(@PathVariable String userName) {
//        return new ResponseEntity<>(userService.findByUsername(userName), HttpStatus.OK);
//    }
    @PutMapping("/{userId}")
    @Operation(summary = "Update a User by ID", description = "Update a single User by their ID.")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long userId, @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.updateUserById(userId, user), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a User by ID", description = "Delete a single User by their ID.")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User with id " + userId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Users", description = "Return all Users.")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
