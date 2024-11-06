package MMS.inventory.controller;

import MMS.inventory.model.User;
import MMS.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Optional<User>> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/{userName}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String userName) {
        return new ResponseEntity<>(userService.findByUsername(userName), HttpStatus.OK);
    }
//    @PutMapping("/{userName}")
//    public ResponseEntity<Optional<User>> updateUser(@PathVariable String userName, @RequestBody User user) {
//        return new ResponseEntity<>(userService.updateUser(userName, user), HttpStatus.OK);
//    }
}
