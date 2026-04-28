package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Education;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Education.GradeType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EducationCreateRequest(

        @NotNull(message = "Profile id is required")
        Long profileId,

        @NotBlank(message = "Degree is required")
        @Size(max = 150)
        String degree,

        @NotBlank(message = "Field of study is required")
        @Size(max = 150)
        String fieldOfStudy,

        @NotBlank(message = "Institution name is required")
        @Size(max = 150)
        String institutionName,

        @Size(max = 150)
        String location,

        @NotNull(message = "Start date is required")
        LocalDate startDate,

        LocalDate endDate,

        Boolean isCurrent,

        GradeType gradeType,

        @Size(max = 20)
        String gradeValue,

        String description,

        @NotNull(message = "Order index is required")
        Integer orderIndex

) {

    public static Education toEntity(EducationCreateRequest request){
        return Education.builder()
                .degree(request.degree())
                .fieldOfStudy(request.fieldOfStudy())
                .institutionName(request.institutionName())
                .location(request.location())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .isCurrent(request.isCurrent() != null && request.isCurrent())
                .gradeType(request.gradeType())
                .gradeValue(request.gradeValue())
                .description(request.description())
                .orderIndex(request.orderIndex())
                .build();
    }
}