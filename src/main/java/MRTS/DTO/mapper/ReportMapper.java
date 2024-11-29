package MRTS.DTO.mapper;

import MRTS.DTO.ReportDto;
import MRTS.domain.Report;
import org.springframework.stereotype.Component;
@Component
public class ReportMapper {
    public ReportDto toReportDto(Report report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setReportId(report.getReportId());
        reportDto.setTestName(report.getTestName());
        reportDto.setSampleType(report.getSampleType());
        reportDto.setTestResult(report.getTestResult());
        reportDto.setTestUnit(report.getTestUnit());
        reportDto.setNormalRange(report.getNormalRange());
        reportDto.setTestStatus(report.getTestStatus());
        reportDto.setComments(report.getComments());
        reportDto.setTestDate(report.getTestDate());
        reportDto.setTestDescription(report.getTestDescription());
        reportDto.setReportGeneratedDate(report.getReportGeneratedDate());
        reportDto.setReportGeneratedBy(report.getReportGeneratedBy());
        reportDto.setLabTechnicianId(report.getLabTechnician().getLabTechnicianId());
        reportDto.setPatientId(report.getPatient().getPatientId());
        reportDto.setDoctorId(report.getDoctor().getDoctorId());
        return reportDto;
    }
    public Report toReport(ReportDto reportDto) {
        Report report = new Report();
        report.setTestName(reportDto.getTestName());
        report.setSampleType(reportDto.getSampleType());
        report.setTestResult(reportDto.getTestResult());
        report.setTestUnit(reportDto.getTestUnit());
        report.setNormalRange(reportDto.getNormalRange());
        report.setTestStatus(reportDto.getTestStatus());
        report.setComments(reportDto.getComments());
        report.setTestDate(reportDto.getTestDate());
        report.setTestDescription(reportDto.getTestDescription());
        report.setReportGeneratedDate(reportDto.getReportGeneratedDate());
        report.setReportGeneratedBy(reportDto.getReportGeneratedBy());
        return report;
    }
}
