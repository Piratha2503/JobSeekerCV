package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProjectCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProjectUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ProjectResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<APIContentResponse<ProjectResponse>> createProject(
            @Valid @RequestBody ProjectCreateRequest request) {

        log.info("API create project");

        ProjectResponse response = projectService.createProject(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new APIContentResponse<>(
                        "201",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Project created successfully",
                        "project",
                        response
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<ProjectResponse>> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectUpdateRequest request) {

        log.info("API update project {}", id);

        ProjectResponse response = projectService.updateProject(id, request);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Project updated successfully",
                        "project",
                        response
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteProject(
            @PathVariable Long id) {

        log.info("API delete project {}", id);

        projectService.deleteProject(id);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Project deleted successfully",
                        "projectId",
                        id
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<ProjectResponse>> getProjectById(
            @PathVariable Long id) {

        log.info("API get project {}", id);

        ProjectResponse response = projectService.getProjectById(id);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Project fetched successfully",
                        "project",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<ProjectResponse>>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort) {

        log.info("API get all projects");

        PaginatedResponse.Pagination pagination =
                PaginatedResponse.Pagination.builder()
                        .pageNumber(page)
                        .pageSize(size)
                        .orderedBy(order)
                        .sortedBy(sort)
                        .build();

        List<ProjectResponse> responses =
                projectService.getAllProjects(pagination);

        return ResponseEntity.ok(
                new PaginatedResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "Projects fetched successfully",
                        "projects",
                        responses,
                        pagination
                )
        );
    }
}