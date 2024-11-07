package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.UserDto;
import MMS.inventory.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getName());
        userDto.setUserStatus(user.getStatus());
        userDto.setUserType(user.getType());
        userDto.setUserRole(user.getRole());
        return userDto;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getUserName());
        user.setStatus(userDto.getUserStatus());
        user.setType(userDto.getUserType());
        user.setRole(userDto.getUserRole());
        return user;
    }
}
