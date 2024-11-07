package MMS.inventory.services;

import MMS.inventory.DTO.UserDto;

import java.util.List;

public interface UserService {
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    UserDto createUser(UserDto user);
    UserDto updateUserById(Long userId, UserDto user);
    void deleteUserById(Long userId);
    List<UserDto> findAllUsers();
}
