package MRTS.services.Impl;

import MRTS.DTO.ReportDto;
import MRTS.DTO.mapper.ReportMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.*;
import MRTS.repository.*;
import MRTS.services.DoctorService;
import MRTS.services.PatientService;
import MRTS.services.ReportService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final ReportRepository reportRepository;
    private final LabTechnicianRepository labTechnicianRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        Patient patient = patientRepository.findById(reportDto.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + reportDto.getPatientId()));
        LabTechnician labTechnician = labTechnicianRepository.findById(reportDto.getLabTechnicianId()).orElseThrow(() -> new ResourceNotFoundException("Lab not found with id: " + reportDto.getLabTechnicianId()));
        Doctor doctor = doctorRepository.findById(reportDto.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + reportDto.getDoctorId()));
        Report report = reportMapper.toReport(reportDto);
        report.setPatient(patient);
        report.setLabTechnician(labTechnician);
        report.setDoctor(doctor);
        Report saveReport = reportRepository.save(report);
        return reportMapper.toReportDto(saveReport);
    }

    @Override
    public ReportDto getReportById(UUID reportId) {
        return reportRepository.findById(reportId).map(reportMapper::toReportDto).orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + reportId));
    }

    @Override
    public ReportDto patchReportById(UUID reportId, ReportDto reportDto) {
        Report existingReport = reportRepository.findById(reportId).orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + reportId));
        if (reportDto.getTestName() != null) {
            existingReport.setTestName(reportDto.getTestName());
        }
        if (reportDto.getSampleType() != null) {
            existingReport.setSampleType(reportDto.getSampleType());
        }
        if (reportDto.getTestResult() != null) {
            existingReport.setTestResult(reportDto.getTestResult());
        }
        if (reportDto.getTestUnit() != null) {
            existingReport.setTestUnit(reportDto.getTestUnit());
        }
        if (reportDto.getNormalRange() != null) {
            existingReport.setNormalRange(reportDto.getNormalRange());
        }
        if (reportDto.getTestStatus() != null) {
            existingReport.setTestStatus(reportDto.getTestStatus());
        }
        if (reportDto.getComments() != null) {
            existingReport.setComments(reportDto.getComments());
        }
        if (reportDto.getTestDate() != null) {
            existingReport.setTestDate(reportDto.getTestDate());
        }
        if (reportDto.getReportGeneratedDate() != null) {
            existingReport.setReportGeneratedDate(reportDto.getReportGeneratedDate());
        }
        if (reportDto.getReportGeneratedBy() != null) {
            existingReport.setReportGeneratedBy(reportDto.getReportGeneratedBy());
        }
        return reportMapper.toReportDto(reportRepository.save(existingReport));
    }

    @Override
    public ReportDto updateReportById(UUID reportId, ReportDto reportDto) {
        Report existingReport = reportRepository.findById(reportId).orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + reportId));
        existingReport.setTestName(reportDto.getTestName());
        existingReport.setSampleType(reportDto.getSampleType());
        existingReport.setTestResult(reportDto.getTestResult());
        existingReport.setTestUnit(reportDto.getTestUnit());
        existingReport.setNormalRange(reportDto.getNormalRange());
        existingReport.setTestStatus(reportDto.getTestStatus());
        existingReport.setComments(reportDto.getComments());
        existingReport.setTestDate(reportDto.getTestDate());
        existingReport.setReportGeneratedDate(reportDto.getReportGeneratedDate());
        existingReport.setReportGeneratedBy(reportDto.getReportGeneratedBy());
        return reportMapper.toReportDto(reportRepository.save(existingReport));
    }


    @Override
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(reportMapper::toReportDto).toList();
    }

    /**
     * @param reportId
     */
    @Override
    public void deleteReportById(UUID reportId) {
        if(reportRepository.existsById(reportId)) {
            reportRepository.deleteById(reportId);
        } else {
            throw new ResourceNotFoundException("Report not found with id: " + reportId);
        }

    }

    /**
     * @param patientId
     * @return
     */
    @Override
    public List<ReportDto> getReportsByPatientId(UUID patientId) {
        List<Report> reports = reportRepository.findByPatient_PatientId(patientId);
        return reports.stream().map(reportMapper::toReportDto).toList();
    }

    /**
     * @param doctorId
     * @return
     */
    @Override
    public List<ReportDto> getReportsByDoctorId(UUID doctorId) {
        List<Report> reports = reportRepository.findByDoctor_DoctorId(doctorId);
        return reports.stream().map(reportMapper::toReportDto).toList();
    }

    /**
     * @param labtechnicianId
     * @return
     */
    @Override
    public List<ReportDto> getReportsByTechnicianId(UUID labtechnicianId) {
        List<Report> reports = reportRepository.findByLabTechnician_LabTechnicianId(labtechnicianId);
        return reports.stream().map(reportMapper::toReportDto).toList();
    }


}
