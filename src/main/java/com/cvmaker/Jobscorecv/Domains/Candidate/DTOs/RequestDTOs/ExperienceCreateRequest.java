package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Common.Enums.EmploymentType;
import com.cvmaker.Jobscorecv.Common.Enums.WorkMode;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Experience;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ExperienceCreateRequest(

        @NotNull(message = "Profile id is required")
        Long profileId,

        @NotBlank(message = "Job title is required")
        @Size(max = 100)
        String jobTitle,

        @NotBlank(message = "Company name is required")
        @Size(max = 100)
        String companyName,

        @Size(max = 150)
        String location,

        @NotNull(message = "Employment type is required")
        EmploymentType employmentType,

        @NotNull(message = "Work mode is required")
        WorkMode workMode,

        @NotNull(message = "Start date is required")
        LocalDate startDate,

        LocalDate endDate,

        boolean isCurrent,

        String description,

        @NotNull(message = "Order index is required")
        Integer orderIndex

) {

    public static Experience toEntity(ExperienceCreateRequest request){
        return Experience.builder()
                .profileId(request.profileId())
                .jobTitle(request.jobTitle())
                .companyName(request.companyName())
                .location(request.location())
                .employmentType(request.employmentType())
                .workMode(request.workMode())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .isCurrent(request.isCurrent())
                .description(request.description())
                .orderIndex(request.orderIndex())
                .build();
    }
}
