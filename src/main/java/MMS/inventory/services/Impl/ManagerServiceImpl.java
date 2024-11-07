package MMS.inventory.services.Impl;

import MMS.inventory.DTO.ManagerDto;
import MMS.inventory.DTO.mapper.ManagerMapper;
import MMS.inventory.model.Manager;
import MMS.inventory.repository.ManagerResository;
import MMS.inventory.services.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerResository managerRepository;
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public ManagerDto getManager(Long managerId) {
        return managerMapper.toManagerDto(managerRepository.findById(managerId).get());
    }

    @Override
    public ManagerDto getManagerByEmail(String email) {
        return managerMapper.toManagerDto(managerRepository.findByEmail(email));
    }

    @Override
    public ManagerDto createManager(ManagerDto manager) {
        return managerMapper.toManagerDto(managerRepository.save(managerMapper.toManager(manager)));
    }

    @Override
    public ManagerDto updateManagerById(Long managerId, ManagerDto managerDto) {
        Manager managerToUpdate = managerRepository.findById(managerId).get();
        Manager manager = managerMapper.toManager(managerDto);
        managerToUpdate.getGeneralDetail().setName(manager.getGeneralDetail().getName());
        managerToUpdate.getGeneralDetail().setGender(manager.getGeneralDetail().getGender());
        managerToUpdate.getGeneralDetail().setEmail(manager.getGeneralDetail().getEmail());
        managerToUpdate.getGeneralDetail().setPhone(manager.getGeneralDetail().getPhone());
        managerToUpdate.getGeneralDetail().getAddress().setAddressLine1(manager.getGeneralDetail().getAddress().getAddressLine1());
        managerToUpdate.getGeneralDetail().getAddress().setAddressLine2(manager.getGeneralDetail().getAddress().getAddressLine2());
        managerToUpdate.getGeneralDetail().getAddress().setCity(manager.getGeneralDetail().getAddress().getCity());
        managerToUpdate.getGeneralDetail().getAddress().setState(manager.getGeneralDetail().getAddress().getState());
        managerToUpdate.getGeneralDetail().getAddress().setCountry(manager.getGeneralDetail().getAddress().getCountry());
        managerToUpdate.getGeneralDetail().getAddress().setZipCode(manager.getGeneralDetail().getAddress().getZipCode());
        return managerMapper.toManagerDto(managerRepository.save(managerToUpdate));

    }

    @Override
    public ManagerDto patchManagerById(Long managerId, ManagerDto managerDto) {
        Manager managerToUpdate = managerRepository.findById(managerId).get();
        Manager manager = managerMapper.toManager(managerDto);
        managerToUpdate.getGeneralDetail().setName(manager.getGeneralDetail().getName());
        managerToUpdate.getGeneralDetail().setGender(manager.getGeneralDetail().getGender());
        managerToUpdate.getGeneralDetail().setEmail(manager.getGeneralDetail().getEmail());
        managerToUpdate.getGeneralDetail().setPhone(manager.getGeneralDetail().getPhone());
        managerToUpdate.getGeneralDetail().getAddress().setAddressLine1(manager.getGeneralDetail().getAddress().getAddressLine1());
        managerToUpdate.getGeneralDetail().getAddress().setAddressLine2(manager.getGeneralDetail().getAddress().getAddressLine2());
        managerToUpdate.getGeneralDetail().getAddress().setCity(manager.getGeneralDetail().getAddress().getCity());
        managerToUpdate.getGeneralDetail().getAddress().setState(manager.getGeneralDetail().getAddress().getState());
        managerToUpdate.getGeneralDetail().getAddress().setCountry(manager.getGeneralDetail().getAddress().getCountry());
        managerToUpdate.getGeneralDetail().getAddress().setZipCode(manager.getGeneralDetail().getAddress().getZipCode());
        return managerMapper.toManagerDto(managerRepository.save(managerToUpdate));
    }

    @Override
    public void deleteManagerById(Long managerId) {
        managerRepository.deleteById(managerId);
    }

    @Override
    public List<ManagerDto> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream().map(managerMapper::toManagerDto).collect(Collectors.toList());
    }
}
