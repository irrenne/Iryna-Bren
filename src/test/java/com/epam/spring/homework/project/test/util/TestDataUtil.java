package com.epam.spring.homework.project.test.util;

import com.epam.spring.homework.project.dto.ReportDto;
import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.model.Report;
import com.epam.spring.homework.project.model.Role;
import com.epam.spring.homework.project.model.Status;
import com.epam.spring.homework.project.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {
    public static final Long ID = 1L;
    public static final String NAME = "Оксана";
    public static final String OTHER_NAME = "Лілія";
    public static final String SURNAME = "Петриківна";
    public static final String LOGIN = "login123";
    public static final String PASSWORD = "passwordD123";

    public static final String EMPTY_FIELD = "";
    public static final String NOT_VALID_PASSWORD = "пароль";

    public static final String TYPE = "type1";
    public static final String OTHER_TYPE = "type2";
    public static final LocalDateTime DATE_OF_CREATION = LocalDateTime.of(2020, 9, 19, 14, 5);
    public static final String COMMENT = "Є проблеми.";
    public static final String FILE_NAME = "report_11.11";

    public static User createUser() {
        return User.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .login(LOGIN)
                .password(PASSWORD)
                .role(Role.CLIENT)
                .build();
    }

    public static UserDto createUserDtoInspector() {
        return UserDto.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .login(LOGIN)
                .password(PASSWORD)
                .role(Role.INSPECTOR)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .login(LOGIN)
                .password(PASSWORD)
                .role(Role.CLIENT)
                .build();
    }

    public static Report createReport() {
        return Report.builder()
                .id(ID)
                .fileName(FILE_NAME)
                .status(Status.SUBMITTED)
                .dateOfCreation(DATE_OF_CREATION)
                .user(createUser())
                .type(TYPE)
                .build();
    }

    public static ReportDto createReportDto() {
        return ReportDto.builder()
                .id(ID)
                .fileName(FILE_NAME)
                .status(Status.SUBMITTED)
                .dateOfCreation(DATE_OF_CREATION)
                .user(createUserDto())
                .type(TYPE)
                .build();
    }
}
