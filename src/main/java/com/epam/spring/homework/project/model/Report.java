package com.epam.spring.homework.project.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Report {
    private long id;
    private String type;
    private Date dateOfCreation;
    private Status status;
    private String comment;
    private String fileName;
    private long userId;
    private long inspectorId;
}
