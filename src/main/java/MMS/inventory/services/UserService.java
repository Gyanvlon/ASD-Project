package MMS.inventory.services;
import MMS.inventory.DTO.LogInDto;
import MMS.inventory.DTO.UserDto;
import MMS.inventory.DTO.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto findByEmail(String email);
    UserResponse createUser(UserDto user);
    UserResponse logInUser(LogInDto user);
    UserDto updateUserById(UUID userId, UserDto user);
    void deleteUserById(UUID userId);
    List<UserDto> findAllUsers();
}
