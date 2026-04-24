package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Common.Enums.EmploymentType;
import com.cvmaker.Jobscorecv.Common.Enums.WorkMode;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Experience;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ExperienceUpdateRequest(

        @Size(max = 100)
        String jobTitle,

        @Size(max = 100)
        String companyName,

        @Size(max = 150)
        String location,

        EmploymentType employmentType,

        WorkMode workMode,

        LocalDate startDate,

        LocalDate endDate,

        Boolean isCurrent,

        String description,

        Integer orderIndex

) {

    public static void updateEntity(Experience experience, ExperienceUpdateRequest request){

        if(request.jobTitle() != null)
            experience.setJobTitle(request.jobTitle());

        if(request.companyName() != null)
            experience.setCompanyName(request.companyName());

        if(request.location() != null)
            experience.setLocation(request.location());

        if(request.employmentType() != null)
            experience.setEmploymentType(request.employmentType());

        if(request.workMode() != null)
            experience.setWorkMode(request.workMode());

        if(request.startDate() != null)
            experience.setStartDate(request.startDate());

        if(request.endDate() != null)
            experience.setEndDate(request.endDate());

        if(request.isCurrent() != null)
            experience.setCurrent(request.isCurrent());

        if(request.description() != null)
            experience.setDescription(request.description());

        if(request.orderIndex() != null)
            experience.setOrderIndex(request.orderIndex());
    }
}