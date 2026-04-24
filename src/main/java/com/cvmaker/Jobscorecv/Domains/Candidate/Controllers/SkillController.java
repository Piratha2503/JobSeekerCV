package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.SkillResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
@Slf4j
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<APIContentResponse<SkillResponse>> createSkill(
            @Valid @RequestBody SkillCreateRequest request) {

        log.info("API create skill");

        SkillResponse response = skillService.createSkill(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "201",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Skill created successfully",
                        "skill",
                        response
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<SkillResponse>> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody SkillUpdateRequest request) {

        log.info("API update skill {}", id);

        SkillResponse response = skillService.updateSkill(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Skill updated successfully",
                        "skill",
                        response
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteSkill(@PathVariable Long id) {

        log.info("API delete skill {}", id);

        skillService.deleteSkill(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Skill deleted",
                        "skillId",
                        id
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<SkillResponse>> getSkillById(@PathVariable Long id) {

        log.info("API get skill {}", id);

        SkillResponse response = skillService.getSkillById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Skill fetched",
                        "skill",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<SkillResponse>>> getAllSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort
    ) {

        log.info("API get all skills");

        PaginatedResponse.Pagination pagination = PaginatedResponse.Pagination.builder()
                .pageNumber(page)
                .pageSize(size)
                .orderedBy(order)
                .sortedBy(sort)
                .build();

        List<SkillResponse> responses = skillService.getAllSkills(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginatedResponse<>(
                        String.valueOf(HttpStatus.OK.value()),
                        com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus.SUCCESS.getStatus(),
                        "Skills fetched successfully",
                        "skills",
                        responses,
                        pagination
                ));
    }
}
