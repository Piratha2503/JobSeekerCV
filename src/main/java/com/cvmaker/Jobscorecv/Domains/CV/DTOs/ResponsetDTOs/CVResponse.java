package com.cvmaker.Jobscorecv.Domains.CV.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.CV.Entities.*;

import java.util.List;

public record CVResponse(

        Long id,
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

    public static CVResponse map(CV cv){

        return new CVResponse(
                cv.getId(),
                cv.getProfileId(),
                cv.getTargetRole(),
                cv.getTargetIndustry(),
                cv.getTemplateType(),
                cv.getSummary(),
                cv.isPublic(),

                // 🔥 flatten child entities → IDs மட்டும் return
                cv.getSelectedExperiences()
                        .stream()
                        .map(SelectedExperience::getSelectedExperienceId)
                        .toList(),

                cv.getSelectedEducations()
                        .stream()
                        .map(SelectedEducation::getSelectedEducationId)
                        .toList(),

                cv.getSelectedCertifications()
                        .stream()
                        .map(SelectedCertification::getSelectedCertificationId)
                        .toList(),

                cv.getSelectedSkills()
                        .stream()
                        .map(SelectedSkill::getSelectedSkillId)
                        .toList(),

                cv.getSelectedProjects()
                        .stream()
                        .map(SelectedProject::getSelectedProjectId)
                        .toList()
        );
    }
}
