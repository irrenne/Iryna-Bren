package com.epam.spring.homework.project.api;

import com.epam.spring.homework.project.dto.ReportDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Report management API")
@RequestMapping("/api/v1/reports")
public interface ReportApi {

    @ApiOperation("Create report")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-report")
    ReportDto createReport(@RequestBody @Valid ReportDto reportDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Report id")
    })
    @ApiOperation("Update report")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update-report/{id}")
    ReportDto updateReport(@PathVariable Long id, @RequestBody @Valid ReportDto reportDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Report id")
    })
    @ApiOperation("Confirm report")
    @PatchMapping("/confirm-report/{id}")
    ReportDto confirmReport(@PathVariable Long id, @RequestBody @Valid ReportDto reportDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Report id"),
            @ApiImplicitParam(name = "comment", paramType = "query", required = true, value = "Reason for denying")
    })
    @ApiOperation("Deny report")
    @PatchMapping("/deny-report/{id}")
    ReportDto denyReport(@PathVariable Long id, @RequestBody @Valid ReportDto reportDto,
                         @RequestParam String comment);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", paramType = "path", required = true,
                    value = "User id whose reports are to be gotten"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Result page to be gotten (0...n)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Records per page to be gotten (0...n)", defaultValue = "7")
    })
    @ApiOperation("Get reports for user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    List<ReportDto> getReportsForUser(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "7") int size);

    @ApiOperation("Get reports")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<ReportDto> getReports();

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Report id")
    })
    @ApiOperation("Delete report")
    @DeleteMapping("/delete-report/{id}")
    ResponseEntity<Void> deleteReport(@PathVariable Long id);
}
