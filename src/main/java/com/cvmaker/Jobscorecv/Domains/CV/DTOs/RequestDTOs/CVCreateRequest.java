package com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.CV.Entities.*;
import java.util.List;


public record CVCreateRequest(
        Long profileId,
        String targetRole,
        String targetIndustry,
        CV.TemplateType templateType,
        String summary,
        boolean isPublic,

        List<Long> experienceIds,
        List<Long> educationIds,
        List<Long> certificationIds,
        List<Long> skillIds,
        List<Long> projectIds
) {



    public static CV toEntity(CVCreateRequest request){

        return CV.builder()
                .targetRole(request.targetRole())
                .targetIndustry(request.targetIndustry())
                .templateType(request.templateType())
                .summary(request.summary())
                .isPublic(request.isPublic())
                .build();
    }
}
