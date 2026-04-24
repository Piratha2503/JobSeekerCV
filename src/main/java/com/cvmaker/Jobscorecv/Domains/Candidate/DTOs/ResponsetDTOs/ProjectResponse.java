package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Project;

import java.time.LocalDate;

public record ProjectResponse(

        Long id,
        Long profileId,
        String projectName,
        String techStack,
        String githubLink,
        String liveLink,
        LocalDate startDate,
        LocalDate endDate,
        boolean isCurrent,
        String description,
        Integer orderIndex

) {

    public static ProjectResponse map(Project project){
        return new ProjectResponse(
                project.getId(),
                project.getProfileId(),
                project.getProjectName(),
                project.getTechStack(),
                project.getGithubLink(),
                project.getLiveLink(),
                project.getStartDate(),
                project.getEndDate(),
                project.isCurrent(),
                project.getDescription(),
                project.getOrderIndex()
        );
    }
}