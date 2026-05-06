package com.cvmaker.Jobscorecv.Domains.Integrations;

import com.cvmaker.Jobscorecv.Domains.Candidate.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateIntegration {

    private final ProfileService profileService;
    private final ExperienceService experienceService;
    private final EducationService educationService;
    private final CertificationService certificationService;
    private final ProjectService projectService;
    private final SkillService skillService;
    private final SkillCategoryService skillCategoryService;


    public boolean validateProfileExists(Long profileId) {
        return profileService.existsById(profileId);
    }


    public boolean validateExperienceExists(Long experienceId) {
        return experienceService.existsById(experienceId);
    }

    public boolean validateEducationExists(Long educationId) {
        return educationService.existsById(educationId);
    }


    public boolean validateCertificationExists(Long certificationId) {
        return certificationService.existsById(certificationId);
    }


    public boolean validateProjectExists(Long projectId) {
        return projectService.existsById(projectId);
    }


    public boolean validateSkillExists(Long skillId) {
        return skillService.existsById(skillId);
    }

    public boolean validateSkillCategoryExists(Long categoryId) {
        return skillCategoryService.existsById(categoryId);
    }
}