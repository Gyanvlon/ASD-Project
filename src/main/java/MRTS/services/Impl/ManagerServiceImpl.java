package MRTS.services.Impl;

import MRTS.DTO.ManagerDto;
import MRTS.DTO.mapper.ManagerMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Address;
import MRTS.domain.Manager;
import MRTS.repository.ManagerResository;
import MRTS.services.ManagerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private final ManagerResository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    public ManagerDto getManager(UUID managerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + managerId));
        return managerMapper.toManagerDto(manager);
    }

    @Override
    public ManagerDto findManagerByEmail(String email) {
        Manager manager = managerRepository.findByGeneralDetail_Email(email).get();
        if(manager == null) {
            throw new ResourceNotFoundException("Manager not found with email: " + email);
        }
        return managerMapper.toManagerDto(manager);
    }


    @Override
    public ManagerDto updateManagerById(UUID managerId, ManagerDto managerDto) {
        Manager managerToUpdate = managerRepository.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + managerId));
        managerToUpdate.getGeneralDetail().setName(managerDto.getManagerName());
        managerToUpdate.getGeneralDetail().setGender(managerDto.getManagerGender());
        managerToUpdate.getGeneralDetail().setEmail(managerDto.getManagerEmail());
        managerToUpdate.getGeneralDetail().setPhone(managerDto.getManagerPhone());
        Address existingAddress = managerToUpdate.getAddress();
        if(existingAddress == null) {
            existingAddress = new Address();
        }
        existingAddress.setAddressLine1(managerDto.getManagerAddress().getAddressLine1());
        existingAddress.setAddressLine2(managerDto.getManagerAddress().getAddressLine2());
        existingAddress.setCity(managerDto.getManagerAddress().getCity());
        existingAddress.setState(managerDto.getManagerAddress().getState());
        existingAddress.setCountry(managerDto.getManagerAddress().getCountry());
        existingAddress.setZipCode(managerDto.getManagerAddress().getZipCode());
        managerToUpdate.setAddress(existingAddress);
        return managerMapper.toManagerDto(managerRepository.save(managerToUpdate));
    }

    @Override
    public ManagerDto patchManagerById(UUID managerId, ManagerDto managerDto) {
        Manager existingManager = managerRepository.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + managerId));
        if(managerDto.getManagerName() != null) {
            existingManager.getGeneralDetail().setName(managerDto.getManagerName());
        }
        if(managerDto.getManagerGender() != null) {
            existingManager.getGeneralDetail().setGender(managerDto.getManagerGender());
        }
        if(managerDto.getManagerEmail() != null) {
            existingManager.getGeneralDetail().setEmail(managerDto.getManagerEmail());
        }
        if(managerDto.getManagerPhone() != null) {
            existingManager.getGeneralDetail().setPhone(managerDto.getManagerPhone());
        }
        if(managerDto.getManagerAddress().getAddressLine1() != null) {
            existingManager.getAddress().setAddressLine1(managerDto.getManagerAddress().getAddressLine1());
        }
        if(managerDto.getManagerAddress().getAddressLine2() != null) {
            existingManager.getAddress().setAddressLine2(managerDto.getManagerAddress().getAddressLine2());
        }
        if(managerDto.getManagerAddress().getCity() != null) {
            existingManager.getAddress().setCity(managerDto.getManagerAddress().getCity());
        }
        if(managerDto.getManagerAddress().getState() != null) {
            existingManager.getAddress().setState(managerDto.getManagerAddress().getState());
        }
        if(managerDto.getManagerAddress().getCountry() != null) {
            existingManager.getAddress().setCountry(managerDto.getManagerAddress().getCountry());
        }
        if(managerDto.getManagerAddress().getZipCode() != null) {
            existingManager.getAddress().setZipCode(managerDto.getManagerAddress().getZipCode());
        }
        return managerMapper.toManagerDto(managerRepository.save(existingManager));
    }

    @Override
    public void deleteManagerById(UUID managerId) {
        if(!managerRepository.existsById(managerId)) {
            throw new ResourceNotFoundException("Manager not found with id: " + managerId);
        }
        managerRepository.deleteById(managerId);
    }

    @Override
    public List<ManagerDto> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream().map(managerMapper::toManagerDto).collect(Collectors.toList());
    }
}
