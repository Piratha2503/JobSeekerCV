package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.SkillResponse;

import java.util.List;

public interface SkillService {

    SkillResponse createSkill(SkillCreateRequest request);

    SkillResponse updateSkill(Long id, SkillUpdateRequest request);

    void deleteSkill(Long id);

    SkillResponse getSkillById(Long id);

    List<SkillResponse> getAllSkills(PaginatedResponse.Pagination pagination);

}