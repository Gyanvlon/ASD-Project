package MMS.inventory.services.Impl;

import MMS.inventory.DTO.LogInDto;
import MMS.inventory.DTO.UserDto;
import MMS.inventory.DTO.UserResponse;
import MMS.inventory.DTO.mapper.UserMapper;
import MMS.inventory.DTO.mapper.UserResponseMapper;
import MMS.inventory.Exception.ResourceNotFoundException;
import MMS.inventory.domain.User;
import MMS.inventory.repository.UserRepository;
import MMS.inventory.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserResponseMapper userResponseMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserResponse createUser(UserDto user) {
        Optional<User> savedUser = userRepository.findByEmail(user.getUserEmail());
        if(savedUser.isPresent()){
            throw new ResourceNotFoundException("User already exists with email: " + user.getUserEmail());
        }
       User savedUser1 = userRepository.save(userMapper.toUser(user));
        return userResponseMapper.toUserResponse(savedUser1);
    }

    @Override
    public UserResponse logInUser(LogInDto user) {
        if (user == null) {
            throw new ResourceNotFoundException("User cannot be null");
        }
        User userToLogIn = userRepository.findByEmail(user.getUserEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + user.getUserEmail()));
        if(!passwordEncoder.matches(user.getUserPassword(), userToLogIn.getPassword())){
            throw new ResourceNotFoundException("Invalid password");
        }
       if(userToLogIn != null){
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword()));
           SecurityContextHolder.getContext().setAuthentication(authentication);
       }
        return userResponseMapper.toUserResponse(userToLogIn);
    }
    @Override
    public UserDto updateUserById(UUID userId, UserDto userDto) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        User user = userMapper.toUser(userDto);
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setStatus(user.getStatus());
        return userMapper.toUserDto(userRepository.save(userToUpdate));
    }

    @Override
    public void deleteUserById(UUID userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }
}
