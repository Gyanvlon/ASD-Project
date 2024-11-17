package MRTS.services.Impl;

import MRTS.DTO.LogInDto;
import MRTS.DTO.UserDto;
import MRTS.DTO.UserResponse;
import MRTS.DTO.mapper.UserMapper;
import MRTS.DTO.mapper.UserResponseMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.*;
import MRTS.repository.*;
import MRTS.services.UserService;
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
    private final DoctorRepository doctorRepository;
    private final ManagerResository managerRepository;
    private final PatientRepository patientRepository;
    private final PharmacistRepository pharmacistRepository;

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userResponseMapper.toUserResponse(user, user.getUserId());
    }

    @Override
    public UserResponse createUser(UserDto user) {
         UUID userId =null;
        Optional<User> savedUser = userRepository.findByEmail(user.getUserEmail());
        if(savedUser.isPresent()){
            throw new ResourceNotFoundException("User already exists with email: " + user.getUserEmail());
        }
       User existingUser = userRepository.save(userMapper.toUser(user));
        if (UserType.ADMIN.equals(user.getRole())) {
            User admin = new User();
            admin.setFullName(existingUser.getFullName());
            admin.setEmail(existingUser.getEmail());
            userId = existingUser.getUserId();
        }
        if(UserType.DOCTOR.equals(user.getRole())){
            Doctor doctor = new Doctor();
            GeneralDetail generalDetail = new GeneralDetail();
            generalDetail.setEmail(existingUser.getEmail());
            generalDetail.setName(existingUser.getFullName());
            doctor.setGeneralDetail(generalDetail);
            doctor.setUser(existingUser);
           Doctor saveDoctor =  doctorRepository.save(doctor);
           userId = saveDoctor.getDoctorId();
        }
        if(UserType.MANAGER.equals(user.getRole())){
            Manager manager = new Manager();
            GeneralDetail generalDetail = new GeneralDetail();
            generalDetail.setEmail(existingUser.getEmail());
            generalDetail.setName(existingUser.getFullName());
            manager.setGeneralDetail(generalDetail);
            manager.setUser(existingUser);
            Manager saveManager =  managerRepository.save(manager);
            userId = saveManager.getManagerId();
        }
        if(UserType.PATIENT.equals(user.getRole())){
            Patient patient = new Patient();
            GeneralDetail generalDetail = new GeneralDetail();
            generalDetail.setEmail(existingUser.getEmail());
            generalDetail.setName(existingUser.getFullName());
            patient.setGeneralDetail(generalDetail);
            patient.setUser(existingUser);
            Patient savePatient =  patientRepository.save(patient);
            userId = savePatient.getPatientId();
        }
        if(UserType.PHARMACIST.equals(user.getRole())){
            Pharmacist pharmacist = new Pharmacist();
            GeneralDetail generalDetail = new GeneralDetail();
            generalDetail.setEmail(existingUser.getEmail());
            generalDetail.setName(existingUser.getFullName());
            pharmacist.setGeneralDetail(generalDetail);
            pharmacist.setUser(existingUser);
            Pharmacist savePharmacist =  pharmacistRepository.save(pharmacist);
            userId = savePharmacist.getPharmacistId();
        }
         userRepository.updateByUserId(existingUser.getId(), userId);
        return userResponseMapper.toUserResponse(existingUser, userId);
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
        return userResponseMapper.toUserResponse(userToLogIn, userToLogIn.getUserId());
    }
    @Override
    public UserResponse updateUserById(UUID userId, UserDto userDto) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        User user = userMapper.toUser(userDto);
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setStatus(user.getStatus());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userRepository.updateByUserId(userToUpdate.getId(), userToUpdate.getUserId());
        return userResponseMapper.toUserResponse(userRepository.save(userToUpdate), userToUpdate.getUserId());
    }

    @Override
    public UserResponse findByUserId(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return userResponseMapper.toUserResponse(user, user.getUserId());
    }

    @Override
    public UserResponse patchUserById(UUID userId, UserDto userDto) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if(userDto.getUserFullName() != null) {
            userToUpdate.setFullName(userDto.getUserFullName());
        }
        if(userDto.getUserEmail() != null) {
            userToUpdate.setEmail(userDto.getUserEmail());
        }
        if(userDto.getUserPassword() != null) {
            userToUpdate.setPassword(userDto.getUserPassword());
        }
        if(userDto.getRole() != null) {
            userToUpdate.setRole(userDto.getRole());
        }
        if(userDto.getUserStatus() != null) {
            userToUpdate.setStatus(userDto.getUserStatus());
        }
        return userResponseMapper.toUserResponse(userRepository.save(userToUpdate), userToUpdate.getUserId());
    }

    @Override
    public void deleteUserById(UUID userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userResponseMapper.toUserResponse(user, user.getUserId())).collect(Collectors.toList());
    }
}
