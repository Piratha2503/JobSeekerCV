package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCategoryCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCategoryUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.SkillCategoryResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.SkillCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skill-categories")
@RequiredArgsConstructor
@Slf4j
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    @PostMapping
    public ResponseEntity<APIContentResponse<SkillCategoryResponse>> createSkillCategory(
            @Valid @RequestBody SkillCategoryCreateRequest request) {

        log.info("API create skill category");

        SkillCategoryResponse response = skillCategoryService.createSkillCategory(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "201",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Skill category created successfully",
                        "skillCategory",
                        response
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<SkillCategoryResponse>> updateSkillCategory(
            @PathVariable Long id,
            @Valid @RequestBody SkillCategoryUpdateRequest request) {

        log.info("API update skill category {}", id);

        SkillCategoryResponse response = skillCategoryService.updateSkillCategory(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Skill category updated successfully",
                        "skillCategory",
                        response
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteSkillCategory(@PathVariable Long id) {

        log.info("API delete skill category {}", id);

        skillCategoryService.deleteSkillCategory(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Skill category deleted",
                        "skillCategoryId",
                        id
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<SkillCategoryResponse>> getSkillCategoryById(@PathVariable Long id) {

        log.info("API get skill category {}", id);

        SkillCategoryResponse response = skillCategoryService.getSkillCategoryById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Skill category fetched",
                        "skillCategory",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<SkillCategoryResponse>>> getAllSkillCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort
    ) {

        log.info("API get all skill categories");

        PaginatedResponse.Pagination pagination = PaginatedResponse.Pagination.builder()
                .pageNumber(page)
                .pageSize(size)
                .orderedBy(order)
                .sortedBy(sort)
                .build();

        List<SkillCategoryResponse> responses = skillCategoryService.getAllSkillCategories(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginatedResponse<>(
                        String.valueOf(HttpStatus.OK.value()),
                        ResponseStatus.SUCCESS.getStatus(),
                        "Skill categories fetched successfully",
                        "skillCategories",
                        responses,
                        pagination
                ));
    }
}
