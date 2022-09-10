package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.model.Status;
import com.epam.spring.homework.project.service.ReportService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @ApiOperation("Create report")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-report")
    public ReportDto createReport(@RequestBody @Valid ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }

    @ApiOperation("Update report")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update-report/{id}")
    public ReportDto updateReport(@PathVariable Long id, @RequestBody @Valid ReportDto reportDto) {
        return reportService.updateReport(id, reportDto);
    }

    @ApiOperation("Confirm report")
    @PatchMapping("/confirm-report/{id}")
    public ReportDto confirmReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        if (Status.SUBMITTED.toString().equals(reportDto.getStatus())) {
            reportDto.setStatus(Status.CONFIRMED.toString());
        }
        return reportService.updateReportInspector(id, reportDto);
    }

    @ApiOperation("Deny report")
    @PatchMapping("/deny-report/{id}")
    public ReportDto denyReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        if (Status.SUBMITTED.toString().equals(reportDto.getStatus())) {
            reportDto.setStatus(Status.NOT_CONFIRMED.toString());
            log.info("denied " + reportDto.getFileName());
        }
        return reportService.updateReportInspector(id, reportDto);
    }

    @ApiOperation("Get reports for user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public List<ReportDto> getReportsForUser(@PathVariable Long userId) {
        return reportService.getReportsForUser(userId);
    }

    @ApiOperation("Get reports")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<ReportDto> getReports() {
        return reportService.getReports();
    }

    @ApiOperation("Delete report")
    @DeleteMapping("/delete-report/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
