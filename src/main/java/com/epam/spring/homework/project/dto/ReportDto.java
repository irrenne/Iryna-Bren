package com.epam.spring.homework.project.dto;

import com.epam.spring.homework.project.model.Status;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ReportDto {
    private long id;

    @NotBlank(message = "type is mandatory")
    @NotNull
    private String type;

    @NotNull
    private LocalDateTime dateOfCreation;

    @NotNull
    private Status status;
    private String comment;

    @NotBlank(message = "fileName is mandatory")
    @NotNull
    private String fileName;

    @NotNull
    private UserDto user;
    private UserDto inspector;
}
