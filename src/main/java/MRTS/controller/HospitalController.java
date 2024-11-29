package MRTS.controller;

import MRTS.DTO.HospitalDto;
import MRTS.domain.Hospital;
import MRTS.repository.HospitalRepository;
import MRTS.services.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Hospital", description = "Hospital API")
@RequestMapping("hospitals")
@AllArgsConstructor
public class HospitalController {
    final HospitalService hospitalService;

    @GetMapping("/name/{hospitalName}")
    ResponseEntity <List<HospitalDto>> getHospitalByName(@PathVariable String hospitalName) {
        return new ResponseEntity<List<HospitalDto>>(hospitalService.getHospitalByName(hospitalName), HttpStatus.OK);
    }
    @PostMapping
    @Operation(summary = "Register Hospital", description = "Register Hospital")
    public ResponseEntity<HospitalDto> createHospital( @Valid @RequestBody HospitalDto hospitalDto) {
        return new ResponseEntity<HospitalDto>(hospitalService.registerHospital(hospitalDto), HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Get All Hospitals", description = "Get All Hospitals")
    public ResponseEntity<List<HospitalDto>> getAllHospitals() {
        return new ResponseEntity(hospitalService.getAllHospitals(), HttpStatus.OK);
    }
//   @GetMapping("/doctorId")
//    @Operation(summary = "Get Hospital by Doctor ID", description = "Get Hospital by Doctor ID")
//    public ResponseEntity<HospitalDto> getHospitalByDoctorId(UUID doctorId) {
//        return new ResponseEntity(hospitalService.getHospitalByDoctorId(doctorId), HttpStatus.OK);
//    }
    @GetMapping("/{hospitalId}")
    @Operation(summary = "Get Hospital by ID", description = "Get Hospital by ID")
    public ResponseEntity<HospitalDto> getHospitalById(@PathVariable UUID hospitalId) {
        return new ResponseEntity(hospitalService.getHospitalById(hospitalId), HttpStatus.OK);
    }
    @DeleteMapping("/{hospitalId}")
    @Operation(summary = "Delete Hospital by ID", description = "Delete Hospital by ID")
    public ResponseEntity<String> deleteHospitalById(@PathVariable UUID hospitalId) {
        hospitalService.deleteHospitalById(hospitalId);
        return new ResponseEntity<>("Hospital with id " + hospitalId + " deleted successfully", HttpStatus.OK);
    }
    @PutMapping("/{hospitalId}")
    @Operation(summary = "Update Hospital by ID", description = "Update Hospital by ID")
    public ResponseEntity<HospitalDto> updateHospitalById(@PathVariable UUID hospitalId, @Valid @RequestBody HospitalDto hospital) {
        return new ResponseEntity<>(hospitalService.updateHospitalById(hospitalId, hospital), HttpStatus.OK);
    }
    @PatchMapping("/{hospitalId}")
    @Operation(summary = "Patch Hospital by ID", description = "Patch Hospital by ID")
    public ResponseEntity<HospitalDto> patchHospitalById(@PathVariable UUID hospitalId, @RequestBody HospitalDto hospital) {
        return new ResponseEntity<>(hospitalService.patchHospitalById(hospitalId, hospital), HttpStatus.OK);
    }
    @GetMapping("/email/{hospitalEmail}")
    @Operation(summary = "Get Hospital by Email", description = "Get Hospital by Email")
    public ResponseEntity<HospitalDto> getHospitalByEmail(@PathVariable String hospitalEmail) {
        return new ResponseEntity<>(hospitalService.getHospitalByEmail(hospitalEmail), HttpStatus.OK);
    }

}