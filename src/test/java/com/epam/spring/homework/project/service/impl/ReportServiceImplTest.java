package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.exception.ReportNotFoundException;
import com.epam.spring.homework.project.model.Report;
import com.epam.spring.homework.project.repository.ReportRepository;
import com.epam.spring.homework.project.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.epam.spring.homework.project.model.Status.CONFIRMED;
import static com.epam.spring.homework.project.model.Status.NOT_CONFIRMED;
import static com.epam.spring.homework.project.test.util.TestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepository;

    @Test
    void getReportTest() {
        Report report = TestDataUtil.createReport();
        when(reportRepository.findById(ID)).thenReturn(Optional.of(report));

        ReportDto reportDto = reportService.getReport(ID);

        assertThat(reportDto, allOf(
                hasProperty("id", equalTo(report.getId())),
                hasProperty("fileName", equalTo(report.getFileName())),
                hasProperty("type", equalTo(report.getType())),
                hasProperty("dateOfCreation", equalTo(report.getDateOfCreation()))
        ));
    }

    @Test
    void getReportNotFoundTest() {
        when(reportRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.getReport(ID));
    }

    @Test
    void createReportTest() {
        Report testReport = TestDataUtil.createReport();
        ReportDto testReportDto = TestDataUtil.createReportDto();
        when(reportRepository.save(ArgumentMatchers.any())).thenReturn(testReport);

        ReportDto reportDto = reportService.createReport(testReportDto);

        assertThat(reportDto, allOf(
                hasProperty("id", equalTo(testReportDto.getId())),
                hasProperty("fileName", equalTo(testReportDto.getFileName())),
                hasProperty("type", equalTo(testReportDto.getType())),
                hasProperty("dateOfCreation", equalTo(testReportDto.getDateOfCreation()))
        ));
    }

    @Test
    void updateReportTest() {
        Report testReport = TestDataUtil.createReport();
        ReportDto testReportDto = TestDataUtil.createReportDto();
        testReportDto.setType(OTHER_TYPE);
        when(reportRepository.findById(ID)).thenReturn(Optional.of(testReport));
        when(reportRepository.save(any())).thenReturn(testReport);

        ReportDto reportDto = reportService.updateReport(ID, testReportDto);

        assertThat(reportDto, allOf(
                hasProperty("id", equalTo(testReportDto.getId())),
                hasProperty("fileName", equalTo(testReportDto.getFileName())),
                hasProperty("type", equalTo(testReportDto.getType())),
                hasProperty("dateOfCreation", equalTo(testReportDto.getDateOfCreation()))
        ));
    }

    @Test
    void updateReportNotFoundTest() {
        ReportDto testReportDto = TestDataUtil.createReportDto();
        when(reportRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.updateReport(ID, testReportDto));
    }

    @Test
    void denyReportTest() {
        Report testReport = TestDataUtil.createReport();
        ReportDto testReportDto = TestDataUtil.createReportDto();
        testReportDto.setInspector(TestDataUtil.createUserDtoInspector());
        when(reportRepository.findById(ID)).thenReturn(Optional.of(testReport));
        when(reportRepository.save(any())).thenReturn(testReport);

        ReportDto reportDto = reportService.denyReport(ID, testReportDto, COMMENT);

        assertThat(reportDto, allOf(
                hasProperty("id", equalTo(testReportDto.getId())),
                hasProperty("comment", equalTo(COMMENT)),
                hasProperty("status", equalTo(NOT_CONFIRMED)),
                hasProperty("inspector", equalTo(testReportDto.getInspector()))
        ));
    }

    @Test
    void confirmReportTest() {
        Report testReport = TestDataUtil.createReport();
        ReportDto testReportDto = TestDataUtil.createReportDto();
        testReportDto.setInspector(TestDataUtil.createUserDtoInspector());
        when(reportRepository.findById(ID)).thenReturn(Optional.of(testReport));
        when(reportRepository.save(any())).thenReturn(testReport);

        ReportDto reportDto = reportService.confirmReport(ID, testReportDto);

        assertThat(reportDto, allOf(
                hasProperty("id", equalTo(testReportDto.getId())),
                hasProperty("status", equalTo(CONFIRMED)),
                hasProperty("inspector", equalTo(testReportDto.getInspector()))
        ));
    }

    @Test
    void confirmNotFoundReportTest() {
        ReportDto testReportDto = TestDataUtil.createReportDto();
        when(reportRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.confirmReport(ID, testReportDto));
    }

    @Test
    void denyNotFoundReportTest() {
        ReportDto testReportDto = TestDataUtil.createReportDto();
        when(reportRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.denyReport(ID, testReportDto, COMMENT));
    }

    @Test
    void getReportsForUserTest() {
        List<Report> reports = List.of(TestDataUtil.createReport(),
                TestDataUtil.createReport());
        when(reportRepository.getReportsForUser(any(), any())).thenReturn(reports);

        List<ReportDto> reportsForUser = reportService.getReportsForUser(ID, 0, 2);

        verify(reportRepository).getReportsForUser(any(), any());
        assertThat(reportsForUser, hasSize(reports.size()));
    }

    @Test
    void getReportsTest() {
        List<Report> reports = List.of(TestDataUtil.createReport(),
                TestDataUtil.createReport());
        when(reportRepository.findAll(any(Sort.class))).thenReturn(reports);

        List<ReportDto> reportsFound = reportService.getReports();

        verify(reportRepository).findAll(any(Sort.class));
        assertThat(reportsFound, hasSize(reports.size()));
    }

    @Test
    void deleteReportTest() {
        Report report = TestDataUtil.createReport();
        when(reportRepository.findById(ID)).thenReturn(Optional.of(report));

        reportService.deleteReport(ID);

        verify(reportRepository, times(1)).delete(report);
    }

    @Test
    void deleteReportNotFoundTest() {
        when(reportRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.deleteReport(ID));
    }
}
