package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Education;
import java.time.LocalDate;

public record EducationResponse(

        Long id,
        Long profileId,
        String degree,
        String fieldOfStudy,
        String institutionName,
        String location,
        LocalDate startDate,
        LocalDate endDate,
        boolean isCurrent,
        Education.GradeType gradeType,
        String gradeValue,
        String description,
        Integer orderIndex

) {

    public static EducationResponse map(Education education){
        return new EducationResponse(
                education.getId(),
                education.getProfile().getId(),
                education.getDegree(),
                education.getFieldOfStudy(),
                education.getInstitutionName(),
                education.getLocation(),
                education.getStartDate(),
                education.getEndDate(),
                education.isCurrent(),
                education.getGradeType(),
                education.getGradeValue(),
                education.getDescription(),
                education.getOrderIndex()
        );
    }
}
