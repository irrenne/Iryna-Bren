package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.api.ReportApi;
import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.model.Status;
import com.epam.spring.homework.project.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReportController implements ReportApi {
    private final ReportService reportService;

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }

    @Override
    public ReportDto updateReport(Long id, ReportDto reportDto) {
        return reportService.updateReport(id, reportDto);
    }

    @Override
    public ReportDto confirmReport(Long id, ReportDto reportDto) {
        if (reportDto.getStatus().equals(Status.SUBMITTED)) {
            reportDto.setStatus(Status.CONFIRMED);
            log.info("report with id = " + id + " confirmed");
        }
        return reportService.updateReportInspector(id, reportDto);
    }

    @Override
    public ReportDto denyReport(Long id, ReportDto reportDto, String comment) {
        if (reportDto.getStatus().equals(Status.SUBMITTED)) {
            reportDto.setStatus(Status.NOT_CONFIRMED);
            reportDto.setComment(comment);
            log.info("denied report with id = " + id + " reason " + comment);
        }
        return reportService.updateReportInspector(id, reportDto);
    }

    @Override
    public List<ReportDto> getReportsForUser(Long userId, int page, int size) {
        return reportService.getReportsForUser(userId, page, size);
    }

    @Override
    public List<ReportDto> getReports() {
        return reportService.getReports();
    }

    @Override
    public ResponseEntity<Void> deleteReport(Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
