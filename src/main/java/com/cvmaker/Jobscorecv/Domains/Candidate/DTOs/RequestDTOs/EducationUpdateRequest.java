package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Education;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Education.GradeType;

import java.time.LocalDate;

public record EducationUpdateRequest(

        String degree,
        String fieldOfStudy,
        String institutionName,
        String location,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isCurrent,
        GradeType gradeType,
        String gradeValue,
        String description,
        Integer orderIndex

) {

    public static void updateEntity(Education education, EducationUpdateRequest request){

        if(request.degree() != null)
            education.setDegree(request.degree());

        if(request.fieldOfStudy() != null)
            education.setFieldOfStudy(request.fieldOfStudy());

        if(request.institutionName() != null)
            education.setInstitutionName(request.institutionName());

        if(request.location() != null)
            education.setLocation(request.location());

        if(request.startDate() != null)
            education.setStartDate(request.startDate());

        if(request.endDate() != null)
            education.setEndDate(request.endDate());

        if(request.isCurrent() != null)
            education.setCurrent(request.isCurrent());

        if(request.gradeType() != null)
            education.setGradeType(request.gradeType());

        if(request.gradeValue() != null)
            education.setGradeValue(request.gradeValue());

        if(request.description() != null)
            education.setDescription(request.description());

        if(request.orderIndex() != null)
            education.setOrderIndex(request.orderIndex());
    }
}