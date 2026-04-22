package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Skill;

public record SkillResponse(
        Long id,
        String skillName,
        Long categoryId,
        String categoryName

) {

    public static SkillResponse map(Skill skill){
        return new SkillResponse(
                skill.getId(),
                skill.getSkillName(),
                skill.getCategory().getId(),
                skill.getCategory().getName()
        );
    }

}
