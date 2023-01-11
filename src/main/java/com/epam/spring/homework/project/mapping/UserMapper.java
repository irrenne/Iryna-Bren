package com.epam.spring.homework.project.mapping;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> usersToDto(List<User> users);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    default User populateUserWithDtoFields(User user, UserDto userDto) {
        if (Objects.nonNull(userDto.getName())) {
            user.setName(userDto.getName());
        }
        if (Objects.nonNull(userDto.getSurname())) {
            user.setSurname(userDto.getSurname());
        }
        if (Objects.nonNull(userDto.getLogin())) {
            user.setLogin(userDto.getLogin());
        }
        return user;
    }
}
