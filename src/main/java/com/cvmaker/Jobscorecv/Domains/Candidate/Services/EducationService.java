package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.EducationCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.EducationUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.EducationResponse;

import java.util.List;

public interface EducationService {

    EducationResponse createEducation(EducationCreateRequest request);

    EducationResponse updateEducation(Long id, EducationUpdateRequest request);

    void deleteEducation(Long id);

    EducationResponse getEducationById(Long id);

    List<EducationResponse> getAllEducations(PaginatedResponse.Pagination pagination);
}