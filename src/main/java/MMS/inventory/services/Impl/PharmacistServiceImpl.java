package MMS.inventory.services.Impl;

import MMS.inventory.DTO.PharmacistDto;
import MMS.inventory.DTO.mapper.PharmacistMapper;
import MMS.inventory.model.Pharmacist;
import MMS.inventory.repository.PharmacistRepository;
import MMS.inventory.services.PharmacistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PharmacistServiceImpl implements PharmacistService {
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private PharmacistMapper pharmacistMapper;

    @Override
    public PharmacistDto getPharmacist(Long pharmacistId) {
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.findById(pharmacistId).get());
    }

    @Override
    public PharmacistDto getPharmacistByEmail(String email) {
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.findByEmail(email));
    }

    @Override
    public PharmacistDto createPharmacist(PharmacistDto pharmacist) {
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.save(pharmacistMapper.toPharmacist(pharmacist)));
    }

    @Override
    public PharmacistDto updatePharmacistById(Long pharmacistId, PharmacistDto pharmacistDto) {
        Pharmacist pharmacistToUpdate = pharmacistRepository.findById(pharmacistId).get();
        Pharmacist pharmacist = pharmacistMapper.toPharmacist(pharmacistDto);
        pharmacistToUpdate.getGeneralDetail().setName(pharmacist.getGeneralDetail().getName());
        pharmacistToUpdate.getGeneralDetail().setGender(pharmacist.getGeneralDetail().getGender());
        pharmacistToUpdate.getGeneralDetail().setEmail(pharmacist.getGeneralDetail().getEmail());
        pharmacistToUpdate.getGeneralDetail().setPhone(pharmacist.getGeneralDetail().getPhone());
        pharmacistToUpdate.getGeneralDetail().getAddress().setAddressLine1(pharmacist.getGeneralDetail().getAddress().getAddressLine1());
        pharmacistToUpdate.getGeneralDetail().getAddress().setAddressLine2(pharmacist.getGeneralDetail().getAddress().getAddressLine2());
        pharmacistToUpdate.getGeneralDetail().getAddress().setCity(pharmacist.getGeneralDetail().getAddress().getCity());
        pharmacistToUpdate.getGeneralDetail().getAddress().setState(pharmacist.getGeneralDetail().getAddress().getState());
        pharmacistToUpdate.getGeneralDetail().getAddress().setCountry(pharmacist.getGeneralDetail().getAddress().getCountry());
        pharmacistToUpdate.getGeneralDetail().getAddress().setZipCode(pharmacist.getGeneralDetail().getAddress().getZipCode());
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.save(pharmacistToUpdate));
    }

    @Override
    public PharmacistDto patchPharmacistById(Long pharmacistId, PharmacistDto pharmacistDto) {
        Pharmacist pharmacistToUpdate = pharmacistRepository.findById(pharmacistId).get();
        Pharmacist pharmacist = pharmacistMapper.toPharmacist(pharmacistDto);
        pharmacistToUpdate.getGeneralDetail().setName(pharmacist.getGeneralDetail().getName());
        pharmacistToUpdate.getGeneralDetail().setGender(pharmacist.getGeneralDetail().getGender());
        pharmacistToUpdate.getGeneralDetail().setEmail(pharmacist.getGeneralDetail().getEmail());
        pharmacistToUpdate.getGeneralDetail().setPhone(pharmacist.getGeneralDetail().getPhone());
        pharmacistToUpdate.getGeneralDetail().getAddress().setAddressLine1(pharmacist.getGeneralDetail().getAddress().getAddressLine1());
        pharmacistToUpdate.getGeneralDetail().getAddress().setAddressLine2(pharmacist.getGeneralDetail().getAddress().getAddressLine2());
        pharmacistToUpdate.getGeneralDetail().getAddress().setCity(pharmacist.getGeneralDetail().getAddress().getCity());
        pharmacistToUpdate.getGeneralDetail().getAddress().setState(pharmacist.getGeneralDetail().getAddress().getState());
        pharmacistToUpdate.getGeneralDetail().getAddress().setCountry(pharmacist.getGeneralDetail().getAddress().getCountry());
        pharmacistToUpdate.getGeneralDetail().getAddress().setZipCode(pharmacist.getGeneralDetail().getAddress().getZipCode());
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.save(pharmacistToUpdate));
    }

    @Override
    public void deletePharmacistById(Long pharmacistId) {
        pharmacistRepository.deleteById(pharmacistId);
    }

    @Override
    public List<PharmacistDto> getAllPharmacists() {
        List<Pharmacist> pharmacists = pharmacistRepository.findAll();
        return pharmacists.stream().map(pharmacistMapper::toPharmacistDto).collect(Collectors.toList());
    }
}
