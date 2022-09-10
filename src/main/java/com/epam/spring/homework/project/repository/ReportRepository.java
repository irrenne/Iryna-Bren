package com.epam.spring.homework.project.repository;

import com.epam.spring.homework.project.model.Report;

import java.util.List;

public interface ReportRepository {
    Report getReport(Long id);

    Report createReport(Report report);

    Report updateReport(Long id, Report report);

    void deleteReport(Long id);

    List<Report> getReportsForUser(Long userId);

    List<Report> getReports();
}
