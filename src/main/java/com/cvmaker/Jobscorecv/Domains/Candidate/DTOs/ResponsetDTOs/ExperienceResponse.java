package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Experience;
import com.cvmaker.Jobscorecv.Common.Enums.EmploymentType;
import com.cvmaker.Jobscorecv.Common.Enums.WorkMode;

import java.time.LocalDate;

public record ExperienceResponse(

        Long id,
        Long profileId,
        String jobTitle,
        String companyName,
        String location,
        EmploymentType employmentType,
        WorkMode workMode,
        LocalDate startDate,
        LocalDate endDate,
        boolean isCurrent,
        String description,
        Integer orderIndex

) {

    public static ExperienceResponse map(Experience experience){
        return new ExperienceResponse(
                experience.getId(),
                experience.getProfileId(),
                experience.getJobTitle(),
                experience.getCompanyName(),
                experience.getLocation(),
                experience.getEmploymentType(),
                experience.getWorkMode(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.isCurrent(),
                experience.getDescription(),
                experience.getOrderIndex()
        );
    }
}