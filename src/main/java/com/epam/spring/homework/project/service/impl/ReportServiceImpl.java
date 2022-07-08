package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.mapping.ReportMapper;
import com.epam.spring.homework.project.model.Report;
import com.epam.spring.homework.project.repository.ReportRepository;
import com.epam.spring.homework.project.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public ReportDto getReport(Long id) {
        log.info("getReport by id {}", id);
        Report report = reportRepository.getReport(id);
        return ReportMapper.INSTANCE.reportToReportDto(report);
    }

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        log.info("createReport for user with id {}", reportDto.getUserId());
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        report = reportRepository.createReport(report);
        return ReportMapper.INSTANCE.reportToReportDto(report);
    }

    @Override
    public ReportDto updateReport(Long id, ReportDto reportDto) {
        log.info("updateReport with id for user with id {}, {}", id, reportDto.getUserId());
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        report = reportRepository.updateReport(id, report);
        return ReportMapper.INSTANCE.reportToReportDto(report);
    }

    @Override
    public ReportDto updateReportInspector(Long id, ReportDto reportDto) {
        log.info("updateReport with id {} for user with id {}, inspector id = {}", id, reportDto.getUserId(), reportDto.getInspectorId());
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        report = reportRepository.updateReport(id, report);
        return ReportMapper.INSTANCE.reportToReportDto(report);
    }

    @Override
    public void deleteReport(Long id) {
        log.info("deleteReport with id {}", id);
        reportRepository.deleteReport(id);
    }

    @Override
    public List<ReportDto> getReportsForUser(Long userId) {
        log.info("getReportsForUser with id {}", userId);
        List<Report> reports = reportRepository.getReportsForUser(userId);
        return ReportMapper.INSTANCE.reportsToDto(reports);
    }

    @Override
    public List<ReportDto> getReports() {
        log.info("getReports");
        List<Report> reports = reportRepository.getReports();
        return ReportMapper.INSTANCE.reportsToDto(reports);
    }
}
