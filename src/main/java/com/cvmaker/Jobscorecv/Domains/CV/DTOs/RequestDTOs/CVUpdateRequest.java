package com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.CV.Entities.*;

import java.util.List;

public record CVUpdateRequest(
        String targetRole,
        String targetIndustry,
        CV.TemplateType templateType,
        String summary,
        Boolean isPublic,

        List<Long> experienceIds,
        List<Long> educationIds,
        List<Long> certificationIds,
        List<Long> skillIds,
        List<Long> projectIds
) {
    public static void updateEntity(CV cv, CVUpdateRequest request){

        if(request.targetRole() != null){
            cv.setTargetRole(request.targetRole());
        }

        if(request.targetIndustry() != null){
            cv.setTargetIndustry(request.targetIndustry());
        }

        if(request.templateType() != null){
            cv.setTemplateType(request.templateType());
        }

        if(request.summary() != null){
            cv.setSummary(request.summary());
        }

        if(request.isPublic() != null){
            cv.setPublic(request.isPublic());
        }

        // 🔥 CHILD REPLACEMENT STRATEGY

        if(request.experienceIds() != null){
            cv.getSelectedExperiences().clear();

            cv.getSelectedExperiences().addAll(
                    request.experienceIds().stream()
                            .map(id -> SelectedExperience.builder()
                                    .cv(cv)
                                    .selectedExperienceId(id)
                                    .build())
                            .toList()
            );
        }

        if(request.educationIds() != null){
            cv.getSelectedEducations().clear();
            cv.getSelectedEducations().addAll(
                    request.educationIds().stream()
                            .map(id -> SelectedEducation.builder()
                                    .cv(cv)
                                    .selectedEducationId(id)
                                    .build())
                            .toList()
            );
        }

        if(request.certificationIds() != null){
            cv.getSelectedCertifications().clear();
            cv.getSelectedCertifications().addAll(
                    request.certificationIds().stream()
                            .map(id -> SelectedCertification.builder()
                                    .cv(cv)
                                    .selectedCertificationId(id)
                                    .build())
                            .toList()
            );
        }

        if(request.skillIds() != null){
            cv.getSelectedSkills().clear();
            cv.getSelectedSkills().addAll(
                    request.skillIds().stream()
                            .map(id -> SelectedSkill.builder()
                                    .cv(cv)
                                    .selectedSkillId(id)
                                    .build())
                            .toList()
            );
        }

        if(request.projectIds() != null){
            cv.getSelectedProjects().clear();
            cv.getSelectedProjects().addAll(
                    request.projectIds().stream()
                            .map(id -> SelectedProject.builder()
                                    .cv(cv)
                                    .selectedProjectId(id)
                                    .build())
                            .toList()
            );
        }
    }
}
