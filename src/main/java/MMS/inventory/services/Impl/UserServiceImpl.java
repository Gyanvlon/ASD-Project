package MMS.inventory.services.Impl;

import MMS.inventory.model.User;
import MMS.inventory.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> createUser(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserById(Long userId, User user) {
        return Optional.empty();
    }

    @Override
    public void deleteUserById(Long userId) {

    }

    @Override
    public Optional<List<User>> findAllUsers() {
        return Optional.empty();
    }
}
