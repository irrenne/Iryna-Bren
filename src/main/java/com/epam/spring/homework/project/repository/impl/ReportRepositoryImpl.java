package com.epam.spring.homework.project.repository.impl;

import com.epam.spring.homework.project.exception.ReportNotFoundException;
import com.epam.spring.homework.project.repository.ReportRepository;
import com.epam.spring.homework.project.model.Report;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReportRepositoryImpl implements ReportRepository {
    private final List<Report> reports = new ArrayList<>();

    @Override
    public Report getReport(Long id) {
        return reports.stream().filter(report -> report.getId() == (id))
                .findFirst()
                .orElseThrow(() -> new ReportNotFoundException("Report is not found"));
    }

    @Override
    public Report createReport(Report report) {
        reports.add(report);
        return report;
    }

    @Override
    public Report updateReport(Long id, Report report) {
        boolean isDeleted = reports.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            reports.add(report);
        } else {
            throw new ReportNotFoundException("Report is not found");
        }
        return report;
    }

    @Override
    public List<Report> getReportsForUser(Long userId) {
        return reports.stream().filter(r -> r.getUserId() == userId)
                .collect(Collectors.toList());
    }


    @Override
    public List<Report> getReports() {
        return reports;
    }

    @Override
    public void deleteReport(Long id) {
        reports.removeIf(u -> u.getId() == id);
    }
}
