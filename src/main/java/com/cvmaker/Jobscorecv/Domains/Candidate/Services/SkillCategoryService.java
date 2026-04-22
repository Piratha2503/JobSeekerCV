package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCategoryCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCategoryUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.SkillCategoryResponse;
import java.util.List;

public interface SkillCategoryService {

    SkillCategoryResponse createSkillCategory(SkillCategoryCreateRequest request);

    SkillCategoryResponse updateSkillCategory(Long id, SkillCategoryUpdateRequest request);

    void deleteSkillCategory(Long id);

    SkillCategoryResponse getSkillCategoryById(Long id);

    List<SkillCategoryResponse> getAllSkillCategories(PaginatedResponse.Pagination pagination);
}
