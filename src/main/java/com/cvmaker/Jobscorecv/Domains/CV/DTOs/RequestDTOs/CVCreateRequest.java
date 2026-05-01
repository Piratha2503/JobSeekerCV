package com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.CV.Entities.*;

import java.util.List;

public record CVCreateRequest(
        Long profileId,
        String targetRole,
        String targetIndustry,
        CV.TemplateType templateType,
        String summary,
        boolean isPublic,

        List<Long> experienceIds,
        List<Long> educationIds,
        List<Long> certificationIds,
        List<Long> skillIds,
        List<Long> projectIds
) {
    public static CV toEntity(CVCreateRequest request){

        CV cv = CV.builder()
                .profileId(request.profileId())
                .targetRole(request.targetRole())
                .targetIndustry(request.targetIndustry())
                .templateType(request.templateType())
                .summary(request.summary())
                .isPublic(request.isPublic())
                .build();

        // set children
        cv.setSelectedExperiences(
                request.experienceIds().stream()
                        .map(id -> SelectedExperience.builder()
                                .cv(cv)
                                .selectedExperienceId(id)
                                .build())
                        .toList()
        );

        cv.setSelectedEducations(
                request.educationIds().stream()
                        .map(id -> SelectedEducation.builder()
                                .cv(cv)
                                .selectedEducationId(id)
                                .build())
                        .toList()
        );

        cv.setSelectedCertifications(
                request.certificationIds().stream()
                        .map(id -> SelectedCertification.builder()
                                .cv(cv)
                                .selectedCertificationId(id)
                                .build())
                        .toList()
        );

        cv.setSelectedSkills(
                request.skillIds().stream()
                        .map(id -> SelectedSkill.builder()
                                .cv(cv)
                                .selectedSkillId(id)
                                .build())
                        .toList()
        );

        cv.setSelectedProjects(
                request.projectIds().stream()
                        .map(id -> SelectedProject.builder()
                                .cv(cv)
                                .selectedProjectId(id)
                                .build())
                        .toList()
        );

        return cv;
    }
}
