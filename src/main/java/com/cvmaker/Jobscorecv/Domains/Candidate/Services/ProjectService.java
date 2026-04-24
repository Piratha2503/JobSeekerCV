package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProjectCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProjectUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectCreateRequest request);

    ProjectResponse updateProject(Long id, ProjectUpdateRequest request);

    void deleteProject(Long id);

    ProjectResponse getProjectById(Long id);

    List<ProjectResponse> getAllProjects(PaginatedResponse.Pagination pagination);
}