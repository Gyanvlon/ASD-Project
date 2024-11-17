package MRTS.services;
import MRTS.DTO.LogInDto;
import MRTS.DTO.UserDto;
import MRTS.DTO.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse findByEmail(String email);
    UserResponse createUser(UserDto user);
    UserResponse logInUser(LogInDto user);
    UserResponse updateUserById(UUID userId, UserDto user);
    UserResponse findByUserId(UUID userId);
    UserResponse patchUserById(UUID userId, UserDto user);
    void deleteUserById(UUID userId);
    List<UserResponse> findAllUsers();
}
