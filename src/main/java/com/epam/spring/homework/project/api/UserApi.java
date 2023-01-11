package com.epam.spring.homework.project.api;

import com.epam.spring.homework.project.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", dataType = "string", paramType = "path",
                    required = true, value = "User login")
    })
    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    UserDto getUser(@PathVariable String login);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserDto createUser(@RequestBody @Valid UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", dataType = "string", paramType = "path",
                    required = true, value = "User login")
    })
    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    UserDto updateUser(@PathVariable String login, @RequestBody @Valid UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Result page to be gotten (0...n)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Records per page to be gotten (0...n)", defaultValue = "7")
    })
    @ApiOperation("Get users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    List<UserDto> getUsers(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "7") int size);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", dataType = "string", paramType = "path",
                    required = true, value = "User login")
    })
    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{login}")
    ResponseEntity<Void> deleteUser(@PathVariable String login);
}
