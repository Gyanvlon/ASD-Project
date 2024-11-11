package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.UserResponse;
import MMS.inventory.domain.User;
import MMS.inventory.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResponseMapper {
    private final JwtService jwtService;
    public UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setJwtToken(jwtService.generateToken(user));
        userResponse.setExpiresIn(jwtService.getExpirationTime());
        userResponse.setUserId(user.getId());
        userResponse.setUserFullName(user.getFullName());
        userResponse.setUserEmail(user.getEmail());
        userResponse.setUserStatus(user.getStatus());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
