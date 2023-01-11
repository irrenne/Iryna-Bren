package com.epam.spring.homework.project.mapping;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    List<ReportDto> reportsToDto(List<Report> reports);

    ReportDto reportToReportDto(Report report);

    Report reportDtoToReport(ReportDto reportDto);

    default Report populateReportWithDtoForUser(Report report, ReportDto reportDto) {
        if (Objects.nonNull(reportDto.getFileName())) {
            report.setFileName(reportDto.getFileName());
        }
        if (Objects.nonNull(reportDto.getType())) {
            report.setType(reportDto.getType());
        }
        if (Objects.nonNull(reportDto.getDateOfCreation())) {
            report.setDateOfCreation(reportDto.getDateOfCreation());
        }
        return report;
    }

    default Report populateReportWithDtoForInspector(Report report, ReportDto reportDto) {
        if (Objects.nonNull(reportDto.getComment())) {
            report.setComment(reportDto.getComment());
        }
        if (Objects.nonNull(reportDto.getInspector())) {
            report.setInspector(UserMapper.INSTANCE.mapUserDtoToUser(reportDto.getInspector()));
        }
        if (Objects.nonNull(reportDto.getStatus())) {
            report.setStatus(reportDto.getStatus());
        }
        return report;
    }
}
