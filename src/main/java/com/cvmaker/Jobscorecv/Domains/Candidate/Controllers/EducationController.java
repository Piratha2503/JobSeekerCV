package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.EducationCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.EducationUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.EducationResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ExperienceResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.EducationService;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/v1/educations")
@RequiredArgsConstructor
@Slf4j
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<APIContentResponse<EducationResponse>> createEducation(
            @Valid @RequestBody EducationCreateRequest request) {

        log.info("API create education");

        EducationResponse response = educationService.createEducation(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "201",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Education created successfully",
                        "education",
                        response
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<EducationResponse>> updateEducation(
            @PathVariable Long id,
            @Valid @RequestBody EducationUpdateRequest request) {

        log.info("API update education {}", id);

        EducationResponse response = educationService.updateEducation(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Education updated successfully",
                        "education",
                        response
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteEducation(@PathVariable Long id) {

        log.info("API delete education {}", id);

        educationService.deleteEducation(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Education deleted",
                        "educationId",
                        id
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<EducationResponse>> getEducationById(@PathVariable Long id) {

        log.info("API get education {}", id);

        EducationResponse response = educationService.getEducationById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Education fetched",
                        "education",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<EducationResponse>>> getAllEducations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort) {

        PaginatedResponse.Pagination pagination =
                PaginatedResponse.Pagination.builder()
                        .pageNumber(page)
                        .pageSize(size)
                        .orderedBy(order)
                        .sortedBy(sort)
                        .build();

        List<EducationResponse> responses =
                educationService.getAllEducations(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginatedResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Educations fetched successfully",
                        "educations",
                        responses,
                        pagination
                ));
    }
}
