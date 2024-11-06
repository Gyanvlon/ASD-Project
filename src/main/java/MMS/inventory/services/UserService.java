package MMS.inventory.services;

import MMS.inventory.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> createUser(User user);
    Optional<User> updateUserById(Long userId, User user);
    void deleteUserById(Long userId);
    Optional<List<User>> findAllUsers();
}
