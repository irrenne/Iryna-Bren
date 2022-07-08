package com.epam.spring.homework.project.mapping;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> usersToDto(List<User> users);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

}
