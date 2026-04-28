package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Project;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ProjectCreateRequest(

        @NotNull(message = "Profile id is required")
        Long profileId,

        @NotBlank(message = "Project name is required")
        @Size(max = 150)
        String projectName,

        String techStack,

        @Size(max = 255)
        String githubLink,

        @Size(max = 255)
        String liveLink,

        LocalDate startDate,

        LocalDate endDate,

        Boolean isCurrent,

        String description,

        @NotNull(message = "Order index is required")
        Integer orderIndex

) {

    public static Project toEntity(ProjectCreateRequest request){
        return Project.builder()
                .projectName(request.projectName())
                .techStack(request.techStack())
                .githubLink(request.githubLink())
                .liveLink(request.liveLink())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .isCurrent(request.isCurrent() != null && request.isCurrent())
                .description(request.description())
                .orderIndex(request.orderIndex())
                .build();
    }
}
