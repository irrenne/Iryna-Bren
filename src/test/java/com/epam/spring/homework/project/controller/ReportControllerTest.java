package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.exception.ReportNotFoundException;
import com.epam.spring.homework.project.service.ReportService;
import com.epam.spring.homework.project.test.config.TestConfig;
import com.epam.spring.homework.project.test.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.epam.spring.homework.project.model.Status.CONFIRMED;
import static com.epam.spring.homework.project.model.Status.NOT_CONFIRMED;
import static com.epam.spring.homework.project.test.util.TestDataUtil.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ReportController.class)
@Import(TestConfig.class)
public class ReportControllerTest {

    @MockBean
    private ReportService reportService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static final String REPORTS_API = "/api/v1/reports";

    @Test
    void getReportsTest() throws Exception {
        ReportDto reportDto = createReportDto();

        when(reportService.getReports()).thenReturn(Collections.singletonList(reportDto));

        mockMvc.perform(get(REPORTS_API + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ID));
    }

    @Test
    void getReportsForUserTest() throws Exception {
        ReportDto reportDto = createReportDto();

        when(reportService.getReportsForUser(anyLong(), anyInt(), anyInt())).thenReturn(Collections.singletonList(reportDto));

        mockMvc.perform(get(REPORTS_API + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ID));
    }

    @Test
    void createReportTest() throws Exception {
        ReportDto reportDto = createReportDto();

        when(reportService.createReport(reportDto)).thenReturn(reportDto);

        mockMvc.perform(post(REPORTS_API + "/create-report")
                        .content(objectMapper.writeValueAsString(reportDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID));
    }

    @Test
    void createNotValidReportTest() throws Exception {
        ReportDto reportDto = createReportDto();
        reportDto.setFileName(EMPTY_FIELD);
        reportDto.setType(EMPTY_FIELD);

        when(reportService.createReport(reportDto)).thenReturn(reportDto);

        mockMvc.perform(post(REPORTS_API + "/create-report")
                        .content(objectMapper.writeValueAsString(reportDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateReportTest() throws Exception {
        ReportDto reportDto = createReportDto();

        when(reportService.updateReport(ID, reportDto)).thenReturn(reportDto);

        mockMvc.perform(put(REPORTS_API + "/update-report/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID));
    }

    @Test
    void updateNotValidReportTest() throws Exception {
        ReportDto reportDto = createReportDto();
        reportDto.setFileName(EMPTY_FIELD);
        reportDto.setType(EMPTY_FIELD);

        when(reportService.updateReport(ID, reportDto)).thenReturn(reportDto);

        mockMvc.perform(put(REPORTS_API + "/update-report/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistingReportTest() throws Exception {
        ReportDto reportDto = createReportDto();

        when(reportService.updateReport(ID, reportDto)).thenThrow(new ReportNotFoundException("Report is not found"));

        mockMvc.perform(put(REPORTS_API + "/update-report/" + ID)
                        .content(objectMapper.writeValueAsString(reportDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete(REPORTS_API + "/delete-report/" + ID))
                .andExpect(status().isNoContent());

        verify(reportService).deleteReport(ID);
    }

    @Test
    void confirmReportTest() throws Exception {
        ReportDto reportDto = createReportDto();
        reportDto.setInspector(TestDataUtil.createUserDtoInspector());
        reportDto.setStatus(CONFIRMED);

        ReportDto bareReportDto = createReportDto();
        bareReportDto.setInspector(TestDataUtil.createUserDtoInspector());

        when(reportService.confirmReport(ID, bareReportDto)).thenReturn(reportDto);

        mockMvc.perform(patch(REPORTS_API + "/confirm-report/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bareReportDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.status").value(CONFIRMED.toString()));
    }

    @Test
    void denyReportTest() throws Exception {
        ReportDto reportDto = createReportDto();
        reportDto.setInspector(TestDataUtil.createUserDtoInspector());
        reportDto.setStatus(NOT_CONFIRMED);
        reportDto.setComment(COMMENT);

        ReportDto bareReportDto = createReportDto();
        bareReportDto.setInspector(TestDataUtil.createUserDtoInspector());


        when(reportService.denyReport(ID, bareReportDto, COMMENT)).thenReturn(reportDto);

        mockMvc.perform(patch(REPORTS_API + "/deny-report/" + ID)
                        .param("comment", COMMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bareReportDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.status").value(NOT_CONFIRMED.toString()))
                .andExpect(jsonPath("$.comment").value(COMMENT));
    }
}
