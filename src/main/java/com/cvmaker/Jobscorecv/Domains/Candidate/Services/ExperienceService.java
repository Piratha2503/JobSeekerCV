package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ExperienceResponse;

import java.util.List;

public interface ExperienceService {

    ExperienceResponse createExperience(ExperienceCreateRequest request);

    ExperienceResponse updateExperience(Long id, ExperienceUpdateRequest request);

    void deleteExperience(Long id);

    ExperienceResponse getExperienceById(Long id);

    List<ExperienceResponse> getAllExperiences(PaginatedResponse.Pagination pagination);
}