package com.epam.spring.homework.project.dto;

import lombok.Data;

import java.sql.Date;
@Data
public class ReportDto {
    private long id;
    private String type;
    private Date dateOfCreation;
    private String status;
    private String comment;
    private String fileName;
    private long userId;
    private long inspectorId;
}
