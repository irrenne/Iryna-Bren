package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.exception.ReportNotFoundException;
import com.epam.spring.homework.project.mapping.ReportMapper;
import com.epam.spring.homework.project.model.Report;
import com.epam.spring.homework.project.model.Status;
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
        Report report = reportRepository.findById(id).orElseThrow(()
                -> new ReportNotFoundException("Report is not found"));
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
        Report persistedReport = reportRepository.findById(id).orElseThrow(()
                -> new ReportNotFoundException("Report is not found"));
        persistedReport = ReportMapper.INSTANCE.populateReportWithDtoForUser(persistedReport, reportDto);
        persistedReport = reportRepository.save(persistedReport);
        log.info("updateReport with id for user with id {}, {}", id, reportDto.getUser().getId());
        return ReportMapper.INSTANCE.reportToReportDto(persistedReport);
    }

    @Transactional
    @Override
    public ReportDto confirmReport(Long id, ReportDto reportDto) {
        Report persistedReport = reportRepository.findById(id).orElseThrow(()
                -> new ReportNotFoundException("Report is not found"));
        if (reportDto.getStatus().equals(Status.SUBMITTED)) {
            reportDto.setStatus(Status.CONFIRMED);
            log.info("report with id = " + id + " confirmed");

            persistedReport = ReportMapper.INSTANCE.populateReportWithDtoForInspector(persistedReport, reportDto);
            persistedReport = reportRepository.save(persistedReport);
            log.info("confirmReport with id {} for user with id {}, inspector id = {}",
                    id, reportDto.getUser().getId(), reportDto.getInspector().getId());
        }

        return ReportMapper.INSTANCE.reportToReportDto(persistedReport);
    }

    @Transactional
    @Override
    public ReportDto denyReport(Long id, ReportDto reportDto, String comment) {
        Report persistedReport = reportRepository.findById(id).orElseThrow(()
                -> new ReportNotFoundException("Report is not found"));
        if (reportDto.getStatus().equals(Status.SUBMITTED)) {
            reportDto.setStatus(Status.NOT_CONFIRMED);
            reportDto.setComment(comment);
            log.info("denied report with id = " + id + " reason " + comment);

            persistedReport = ReportMapper.INSTANCE.populateReportWithDtoForInspector(persistedReport, reportDto);
            persistedReport = reportRepository.save(persistedReport);
            log.info("denyReport with id {} for user with id {}, inspector id = {}",
                    id, reportDto.getUser().getId(), reportDto.getInspector().getId());
        }

        return ReportMapper.INSTANCE.reportToReportDto(persistedReport);
    }

    @Override
    public void deleteReport(Long id) {
        log.info("deleteReport with id {}", id);
        Report report = reportRepository.findById(id).orElseThrow(()
                -> new ReportNotFoundException("Report is not found"));
        reportRepository.delete(report);
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
