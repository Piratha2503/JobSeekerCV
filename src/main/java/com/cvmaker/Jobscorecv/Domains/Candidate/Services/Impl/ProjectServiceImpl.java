package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProjectCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProjectUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ProjectResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Project;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.ProjectRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Override
    public ProjectResponse createProject(ProjectCreateRequest request) {

        log.info("Creating project for profile {}", request.profileId());

        Project project = repository.save(ProjectCreateRequest.toEntity(request));

        return ProjectResponse.map(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectUpdateRequest request) {

        log.info("Updating project {}", id);

        Project project = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found id:- " + id));

        ProjectUpdateRequest.updateEntity(project, request);

        repository.save(project);

        return ProjectResponse.map(project);
    }

    @Override
    public void deleteProject(Long id) {

        log.info("Deleting project {}", id);

        Project project = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found id:- " + id));

        repository.delete(project);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {

        log.info("Fetching project {}", id);

        Project project = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found id:- " + id));

        return ProjectResponse.map(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects(PaginatedResponse.Pagination pagination) {

        Sort sortBy = pagination.getOrderedBy().equalsIgnoreCase("DESC")
                ? Sort.by(pagination.getSortedBy()).descending()
                : Sort.by(pagination.getSortedBy()).ascending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sortBy
        );

        Page<Project> projects = repository.findAll(pageable);

        pagination.setTotalPages(projects.getTotalPages());
        pagination.setTotalRecords(projects.getTotalElements());

        return projects.stream()
                .map(ProjectResponse::map)
                .toList();
    }
}
