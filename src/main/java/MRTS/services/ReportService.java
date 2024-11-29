package MRTS.services;

import MRTS.DTO.ReportDto;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    ReportDto createReport(ReportDto reportDto);
    ReportDto getReportById(UUID reportId);
    ReportDto patchReportById(UUID reportId, ReportDto reportDto);
    ReportDto updateReportById(UUID reportId, ReportDto reportDto);
    List<ReportDto> getAllReports();
    void deleteReportById(UUID reportId);
    List<ReportDto> getReportsByPatientId(UUID patientId);
    List<ReportDto> getReportsByDoctorId(UUID doctorId);
    List<ReportDto> getReportsByTechnicianId(UUID labtechnicianId);
}
