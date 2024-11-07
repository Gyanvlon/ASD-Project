package MMS.inventory.services;

import MMS.inventory.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    User createUser(User user);
    User updateUserById(Long userId, User user);
    void deleteUserById(Long userId);
    List<User> findAllUsers();
}
