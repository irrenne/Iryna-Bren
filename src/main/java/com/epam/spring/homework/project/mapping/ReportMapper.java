package com.epam.spring.homework.project.mapping;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    List<ReportDto> reportsToDto(List<Report> reports);

    ReportDto reportToReportDto(Report report);

    Report reportDtoToReport(ReportDto reportDto);
}
