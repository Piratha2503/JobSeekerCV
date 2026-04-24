package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Project;

import java.time.LocalDate;

public record ProjectUpdateRequest(

        String projectName,
        String techStack,
        String githubLink,
        String liveLink,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isCurrent,
        String description,
        Integer orderIndex

) {

    public static void updateEntity(Project project, ProjectUpdateRequest request){

        if(request.projectName() != null)
            project.setProjectName(request.projectName());

        if(request.techStack() != null)
            project.setTechStack(request.techStack());

        if(request.githubLink() != null)
            project.setGithubLink(request.githubLink());

        if(request.liveLink() != null)
            project.setLiveLink(request.liveLink());

        if(request.startDate() != null)
            project.setStartDate(request.startDate());

        if(request.endDate() != null)
            project.setEndDate(request.endDate());

        if(request.isCurrent() != null)
            project.setCurrent(request.isCurrent());

        if(request.description() != null)
            project.setDescription(request.description());

        if(request.orderIndex() != null)
            project.setOrderIndex(request.orderIndex());
    }
}