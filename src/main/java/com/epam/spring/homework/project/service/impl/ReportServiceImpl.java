package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.mapping.ReportMapper;
import com.epam.spring.homework.project.model.Report;
import com.epam.spring.homework.project.repository.ReportRepository;
import com.epam.spring.homework.project.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public ReportDto getReport(Long id) {
        log.info("getReport by id {}", id);
        Report report = reportRepository.getById(id);
        return ReportMapper.INSTANCE.reportToReportDto(report);
    }

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        log.info("createReport for user with id {}", reportDto.getUser().getId());
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        report = reportRepository.save(report);
        return ReportMapper.INSTANCE.reportToReportDto(report);
    }

    @Transactional
    @Override
    public ReportDto updateReport(Long id, ReportDto reportDto) {
        log.info("updateReport with id for user with id {}, {}", id, reportDto.getUser().getId());
        Report persistedReport = reportRepository.getById(id);
        persistedReport = ReportMapper.INSTANCE.populateReportWithDtoForUser(persistedReport, reportDto);
        persistedReport = reportRepository.save(persistedReport);
        return ReportMapper.INSTANCE.reportToReportDto(persistedReport);
    }

    @Transactional
    @Override
    public ReportDto updateReportInspector(Long id, ReportDto reportDto) {
        log.info("updateReport with id {} for user with id {}, inspector id = {}",
                id, reportDto.getUser().getId(), reportDto.getInspector().getId());
        Report persistedReport = reportRepository.getById(id);
        persistedReport = ReportMapper.INSTANCE.populateReportWithDtoForInspector(persistedReport, reportDto);
        persistedReport = reportRepository.save(persistedReport);
        return ReportMapper.INSTANCE.reportToReportDto(persistedReport);
    }

    @Override
    public void deleteReport(Long id) {
        log.info("deleteReport with id {}", id);
        reportRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReportDto> getReportsForUser(Long userId, int page, int size) {
        log.info("getReportsForUser with id {}", userId);
        Pageable pageable = PageRequest.of(page, size);
        List<Report> reports = reportRepository.getReportsForUser(userId, pageable);
        return ReportMapper.INSTANCE.reportsToDto(reports);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReportDto> getReports() {
        log.info("getReports");
        List<Report> reports = reportRepository.findAll(Sort.by("dateOfCreation").descending());
        return ReportMapper.INSTANCE.reportsToDto(reports);
    }
}
