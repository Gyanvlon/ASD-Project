package MRTS.services.Impl;

import MRTS.DTO.AddressDto;
import MRTS.DTO.DoctorDto;
import MRTS.DTO.DrugDto;
import MRTS.DTO.mapper.DoctorMapper;
import MRTS.domain.Address;
import MRTS.domain.Doctor;
import MRTS.domain.GeneralDetail;
import MRTS.domain.Hospital;
import MRTS.repository.DoctorRepository;
import MRTS.repository.HospitalRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DoctorServiceImplTest {
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorMapper doctorMapper;
    @Mock
    private HospitalRepository hospitalRepository;
    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;
    private DoctorDto doctorDto;
    private AddressDto addressDto;
    private Doctor doctor;
    private Address address;
    private GeneralDetail generalDetail;
    private Hospital hospital;

    @BeforeEach
    void setUp() {
        UUID fixedUuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        doctorDto = new DoctorDto();
        doctorDto.setDoctorName("Dr. John Doe");
        doctorDto.setDoctorEmail("test@user.com");
        doctorDto.setDoctorPhone(1234567890L);
        doctorDto.setDoctorGender("Male");
        doctorDto.setDoctorSpecialization("Cardiology");
        doctorDto.setDoctorExperience(5);
        doctorDto.setDoctorDepartment("Cardiology");
        doctorDto.setDoctorLicense("1234567890");
        doctorDto.setDoctorQualification("MD");
        doctorDto.setHospitalId(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        addressDto = new AddressDto();
        addressDto.setAddressId(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));
        addressDto.setAddressLine1("123 Main Street");
        addressDto.setCity("New York");
        addressDto.setState("NY");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        doctorDto.setDoctorAddress(addressDto);

        doctor = new Doctor();
//        doctor.setDoctorId(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"));
        generalDetail = new GeneralDetail();
        doctor.setGeneralDetail(generalDetail);
        generalDetail.setName("Dr. John Doe");
        generalDetail.setEmail("test@user.com");
        generalDetail.setPhone(1234567890L);
        generalDetail.setGender("Male");
        doctor.setSpecialization("Cardiology");
        doctor.setExperience(5);
        doctor.setDepartment("Cardiology");
        doctor.setLicense("1234567890");
        doctor.setQualification("MD");
        Hospital hospital = new Hospital();
        hospital.setHospitalId(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        doctor.setHospital(hospital);
        address = new Address();
//        address.setAddressId(fixedUuid);
        address.setAddressLine1("123 Main Street");
        address.setCity("New York");
        address.setState("NY");
        address.setCountry("USA");
        address.setZipCode(10001);
        doctor.setAddress(address);
    }


    @AfterEach
    void tearDown() {
        Mockito.reset(doctorRepository, doctorMapper);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(1)
    @DisplayName("Test getting a new doctor")
    void shouldGetDoctor_whenDoctorExists(UUID fixedUuid) {
        Mockito.when(doctorRepository.findById(fixedUuid)).thenReturn(Optional.of(doctor));
        Mockito.when(doctorMapper.toDoctorDto(doctor)).thenReturn(doctorDto);

        DoctorDto retrievedDoctor = doctorServiceImpl.getDoctor(fixedUuid);

        assertNotNull(retrievedDoctor);
        assertAll(
                () -> assertEquals(doctorDto.getDoctorName(), retrievedDoctor.getDoctorName()),
                () -> assertEquals(doctorDto.getDoctorEmail(), retrievedDoctor.getDoctorEmail()),
                () -> assertEquals(doctorDto.getDoctorPhone(), retrievedDoctor.getDoctorPhone()),
                () -> assertEquals(doctorDto.getDoctorGender(), retrievedDoctor.getDoctorGender()),
                () -> assertEquals(doctorDto.getDoctorSpecialization(), retrievedDoctor.getDoctorSpecialization())
        );

        Mockito.verify(doctorRepository).findById(fixedUuid);
        Mockito.verify(doctorMapper).toDoctorDto(doctor);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@user.com"})
    @Order(2)
    @DisplayName("Test getting a doctor by email")
    void shouldGetDoctorByEmail_whenDoctorExists(String email) {
        Mockito.when(doctorMapper.toDoctor(doctorDto)).thenReturn(doctor);
        Mockito.when(doctorMapper.toDoctorDto(doctor)).thenReturn(doctorDto);
        Mockito.when(doctorRepository.findByGeneralDetail_Email(email)).thenReturn(Optional.of(doctor));
        DoctorDto retrievedDoctor = doctorServiceImpl.getDoctorByEmail(email);

        System.out.println(retrievedDoctor);
        assertNotNull(retrievedDoctor);
        assertEquals(doctorDto.getDoctorName(), retrievedDoctor.getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), retrievedDoctor.getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), retrievedDoctor.getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), retrievedDoctor.getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), retrievedDoctor.getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), retrievedDoctor.getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), retrievedDoctor.getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), retrievedDoctor.getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), retrievedDoctor.getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), retrievedDoctor.getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), retrievedDoctor.getDoctorAddress().getAddressLine1());
        assertEquals(doctorDto.getDoctorAddress().getCity(), retrievedDoctor.getDoctorAddress().getCity());
        assertEquals(doctorDto.getDoctorAddress().getState(), retrievedDoctor.getDoctorAddress().getState());
        assertEquals(doctorDto.getDoctorAddress().getCountry(), retrievedDoctor.getDoctorAddress().getCountry());
        assertEquals(doctorDto.getDoctorAddress().getZipCode(), retrievedDoctor.getDoctorAddress().getZipCode());
        Mockito.verify(doctorRepository).findByGeneralDetail_Email(email);
        Mockito.verify(doctorMapper).toDoctorDto(doctor);
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
//    @Order(3)
//    @DisplayName("Test updating a doctor by id")
//    void shouldUpdateDoctor_whenDoctorExists(UUID doctorId) {
//        // Mock behavior
//        Mockito.when(doctorMapper.toDoctor(Mockito.any(DoctorDto.class))).thenReturn(doctor);
//        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));
//        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
//        Mockito.when(doctorMapper.toDoctorDto(Mockito.any(Doctor.class))).thenReturn(doctorDto);
//
//        // Update the DTO
//        doctorDto.setDoctorName("Dr. Jane Doe updated");
//        doctorDto.setDoctorEmail("test@user1.com");
//
//        // Call the service method
//        DoctorDto updatedDoctorDto = doctorServiceImpl.updateDoctorById(doctorId, doctorDto);
//
//        // Assertions
//        assertNotNull(updatedDoctorDto);
//        assertEquals("Dr. Jane Doe updated", updatedDoctorDto.getDoctorName());
//        assertEquals("test@user1.com", updatedDoctorDto.getDoctorEmail());
//
//        // Verify interactions
//        Mockito.verify(doctorRepository).findById(doctorId);
//        Mockito.verify(doctorRepository).save(doctor);
//        Mockito.verify(doctorMapper).toDoctor(doctorDto);
//        Mockito.verify(doctorMapper).toDoctorDto(doctor);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
//    @Order(4)
//    @DisplayName("Test patching a doctor by id")
//    void shouldPatchDoctor_whenDoctorExists(UUID doctorId) {
//        Mockito.when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorMapper.toDoctor(doctorDto))).thenThrow(new IllegalArgumentException("Invalid Doctor data"));
//        Mockito.when(doctorRepository.save(doctorMapper.toDoctor(doctorDto))).thenReturn(doctorMapper.toDoctor(doctorDto));
//        doctorDto.setDoctorName("Dr. Jane Doe patched");
//        DoctorDto patchedDoctorDto = doctorServiceImpl.patchDoctorById(doctorId, doctorDto);
//        System.out.println(patchedDoctorDto);
//        assertNotNull(patchedDoctorDto);
//        assertEquals(doctorDto.getDoctorName(), patchedDoctorDto.getDoctorName());
//        assertEquals(doctorDto.getDoctorEmail(), patchedDoctorDto.getDoctorEmail());
//        assertEquals(doctorDto.getDoctorPhone(), patchedDoctorDto.getDoctorPhone());
//        assertEquals(doctorDto.getDoctorGender(), patchedDoctorDto.getDoctorGender());
//        assertEquals(doctorDto.getDoctorSpecialization(), patchedDoctorDto.getDoctorSpecialization());
//        assertEquals(doctorDto.getDoctorExperience(), patchedDoctorDto.getDoctorExperience());
//        assertEquals(doctorDto.getDoctorDepartment(), patchedDoctorDto.getDoctorDepartment());
//        assertEquals(doctorDto.getDoctorLicense(), patchedDoctorDto.getDoctorLicense());
//        assertEquals(doctorDto.getDoctorQualification(), patchedDoctorDto.getDoctorQualification());
//        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), patchedDoctorDto.getDoctorAddress().getAddressLine1());
//        assertEquals(doctorDto.getDoctorAddress().getCity(), patchedDoctorDto.getDoctorAddress().getCity());
//        assertEquals(doctorDto.getDoctorAddress().getState(), patchedDoctorDto.getDoctorAddress().getState());
//        assertEquals(doctorDto.getDoctorAddress().getCountry(), patchedDoctorDto.getDoctorAddress().getCountry());
//        assertEquals(doctorDto.getDoctorAddress().getZipCode(), patchedDoctorDto.getDoctorAddress().getZipCode());
//        Mockito.verify(doctorRepository).findById(doctorId);
//    }
    @Test
    @Order(5)
    @DisplayName("Test getting all doctors")
    void shouldGetAllDoctors_whenAllDoctorsExist() {
        Mockito.when(doctorMapper.toDoctor(doctorDto)).thenReturn(doctor);
        Mockito.when(doctorRepository.findAll()).thenReturn(List.of(doctor));
        Mockito.when(doctorMapper.toDoctorDto(doctor)).thenReturn(doctorDto);

        List<DoctorDto> allDoctors = doctorServiceImpl.getAllDoctors();

        // Assertions
        assertNotNull(allDoctors);
        assertEquals(1, allDoctors.size());
        assertAll(
                () -> assertEquals(doctorDto.getDoctorName(), allDoctors.get(0).getDoctorName()),
                () -> assertEquals(doctorDto.getDoctorEmail(), allDoctors.get(0).getDoctorEmail()),
                () -> assertEquals(doctorDto.getDoctorPhone(), allDoctors.get(0).getDoctorPhone()),
                () -> assertEquals(doctorDto.getDoctorGender(), allDoctors.get(0).getDoctorGender()),
                () -> assertEquals(doctorDto.getDoctorSpecialization(), allDoctors.get(0).getDoctorSpecialization()),
                () -> assertEquals(doctorDto.getDoctorExperience(), allDoctors.get(0).getDoctorExperience()),
                () -> assertEquals(doctorDto.getDoctorDepartment(), allDoctors.get(0).getDoctorDepartment()),
                () -> assertEquals(doctorDto.getDoctorLicense(), allDoctors.get(0).getDoctorLicense()),
                () -> assertEquals(doctorDto.getDoctorQualification(), allDoctors.get(0).getDoctorQualification()),
                () -> assertEquals(doctorDto.getHospitalId(), allDoctors.get(0).getHospitalId()),
                () -> assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), allDoctors.get(0).getDoctorAddress().getAddressLine1()),
                () -> assertEquals(doctorDto.getDoctorAddress().getCity(), allDoctors.get(0).getDoctorAddress().getCity()),
                () -> assertEquals(doctorDto.getDoctorAddress().getState(), allDoctors.get(0).getDoctorAddress().getState()),
                () -> assertEquals(doctorDto.getDoctorAddress().getCountry(), allDoctors.get(0).getDoctorAddress().getCountry()),
                () -> assertEquals(doctorDto.getDoctorAddress().getZipCode(), allDoctors.get(0).getDoctorAddress().getZipCode())
        );
        Mockito.verify(doctorRepository).findAll();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(7)
    @DisplayName("Test deleting a doctor by ID")
    void shouldDeleteDcotorById_whenDoctorExists(UUID fixedUuid) {
        Mockito.when(doctorRepository.existsById(fixedUuid)).thenReturn(true);
        doctorServiceImpl.deleteDoctorById(fixedUuid);
        Mockito.verify(doctorRepository).deleteById(fixedUuid);
    }


}