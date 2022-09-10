package com.epam.spring.homework.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class ReportDto {
    @JsonProperty(access = READ_ONLY)
    private long id;

    @NotBlank(message = "type is mandatory")
    @NotNull
    private String type;

    private Date dateOfCreation;

    @NotNull
    private String status;

    private String comment;

    @NotBlank(message = "fileName is mandatory")
    @NotNull
    private String fileName;

    @NotNull
    private long userId;

    private long inspectorId;
}
