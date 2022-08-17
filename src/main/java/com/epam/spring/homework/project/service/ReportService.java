package com.epam.spring.homework.project.service;

import com.epam.spring.homework.project.dto.ReportDto;

import java.util.List;

public interface ReportService {
    ReportDto getReport(Long id);

    ReportDto createReport(ReportDto report);

    ReportDto updateReport(Long id, ReportDto reportDto);

    ReportDto confirmReport(Long id, ReportDto report);

    ReportDto denyReport(Long id, ReportDto report, String comment);

    void deleteReport(Long id);

    List<ReportDto> getReportsForUser(Long userId, int page, int size);

    List<ReportDto> getReports();
}
