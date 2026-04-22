package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.SkillCategory;

public record SkillCategoryResponse(
        Long id,
        String name,
        Integer orderIndex
) {
    public static SkillCategoryResponse map(SkillCategory category){

        return new SkillCategoryResponse(category.getId(), category.getName(), category.getOrderIndex());

    }
}
