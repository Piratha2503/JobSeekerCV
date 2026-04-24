package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ExperienceResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/experiences")
@RequiredArgsConstructor
@Slf4j
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ResponseEntity<APIContentResponse<ExperienceResponse>> createExperience(
            @Valid @RequestBody ExperienceCreateRequest request) {

        log.info("API create experience");

        ExperienceResponse response = experienceService.createExperience(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "201",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Experience created successfully",
                        "experience",
                        response
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<ExperienceResponse>> updateExperience(
            @PathVariable Long id,
            @Valid @RequestBody ExperienceUpdateRequest request) {

        log.info("API update experience {}", id);

        ExperienceResponse response = experienceService.updateExperience(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Experience updated successfully",
                        "experience",
                        response
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteExperience(@PathVariable Long id) {

        log.info("API delete experience {}", id);

        experienceService.deleteExperience(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Experience deleted",
                        "experienceId",
                        id
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<ExperienceResponse>> getExperienceById(@PathVariable Long id) {

        log.info("API get experience {}", id);

        ExperienceResponse response = experienceService.getExperienceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Experience fetched",
                        "experience",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<ExperienceResponse>>> getAllExperiences(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort
    ) {

        log.info("API get all experiences");

        PaginatedResponse.Pagination pagination = PaginatedResponse.Pagination.builder()
                .pageNumber(page)
                .pageSize(size)
                .orderedBy(order)
                .sortedBy(sort)
                .build();

        List<ExperienceResponse> responses = experienceService.getAllExperiences(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginatedResponse<>(
                        String.valueOf(HttpStatus.OK.value()),
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Experiences fetched successfully",
                        "experiences",
                        responses,
                        pagination
                ));
    }
}
