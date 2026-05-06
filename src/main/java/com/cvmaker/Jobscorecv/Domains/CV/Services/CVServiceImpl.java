package com.cvmaker.Jobscorecv.Domains.CV.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVCreateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.ResponsetDTOs.CVResponse;
import com.cvmaker.Jobscorecv.Domains.CV.Entities.*;
import com.cvmaker.Jobscorecv.Domains.CV.Repositories.CVRepository;
import com.cvmaker.Jobscorecv.Domains.Integrations.CandidateIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CVServiceImpl implements CVService {

    private final CVRepository repository;
    private final CandidateIntegration candidateIntegration;

    @Override
    public CVResponse create(CVCreateRequest request) {

        CV cv = CVCreateRequest.toEntity(request);

        copyCVProperties(cv,request);

        return CVResponse.map(repository.save(cv));
    }

    @Override
    public CVResponse update(Long id, CVUpdateRequest request) {

        CV cv = repository.findByIdWithAll(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));

        CVUpdateRequest.updateEntity(cv, request);

        return CVResponse.map(repository.save(cv));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CVResponse getById(Long id) {

        // 🔥 NO N+1
        CV cv = repository.findByIdWithAll(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));

        return CVResponse.map(cv);
    }

    @Override
    public List<CVResponse> getAll(PaginatedResponse.Pagination pagination) {

        Sort sort = pagination.getOrderedBy().equalsIgnoreCase("asc") ?
                Sort.by(pagination.getSortedBy()).ascending() :
                Sort.by(pagination.getSortedBy()).descending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sort
        );

        Page<CV> page = repository.findAll(pageable);

        pagination.setTotalPages(page.getTotalPages());
        pagination.setTotalRecords(page.getTotalElements());

        return page.getContent()
                .stream()
                .map(CVResponse::map)
                .toList();
    }

    private void copyCVProperties(CV cv, CVCreateRequest request) {

        if (!candidateIntegration.validateProfileExists(request.profileId()))
            throw new EntityNotFoundException("Experience Not Found for ID "+request.profileId());

        cv.setProfileId(request.profileId());

        cv.setSelectedExperiences(
                request.experienceIds().stream()
                        .map(id -> {
                            if (!candidateIntegration.validateExperienceExists(id))
                                throw new EntityNotFoundException("Experience Not Found for ID "+id);
                            return SelectedExperience.builder()
                                    .cv(cv)
                                    .selectedExperienceId(id)
                                    .build();
                        })
                        .toList()
        );

        cv.setSelectedEducations(
                request.educationIds().stream()
                        .map(id -> {
                            if (!candidateIntegration.validateEducationExists(id))
                                throw new EntityNotFoundException("Education Not Found for ID "+id);
                            return SelectedEducation.builder()
                                    .cv(cv)
                                    .selectedEducationId(id)
                                    .build();
                        })
                        .toList()
        );

        cv.setSelectedCertifications(
                request.certificationIds().stream()
                        .map(id -> {
                            if (!candidateIntegration.validateCertificationExists(id))
                                throw new EntityNotFoundException("Certification Not Found for ID "+id);
                            return SelectedCertification.builder()
                                    .cv(cv)
                                    .selectedCertificationId(id)
                                    .build();
                        })
                        .toList());

        cv.setSelectedSkills(
                request.skillIds().stream()
                        .map(id -> {
                            if (!candidateIntegration.validateSkillExists(id))
                                throw new EntityNotFoundException("Skill Not Found for ID "+id);
                            return SelectedSkill.builder()
                                    .cv(cv)
                                    .selectedSkillId(id)
                                    .build();
                        })
                        .toList()
        );

        cv.setSelectedProjects(
                request.projectIds().stream()
                        .map(id -> {
                            if (!candidateIntegration.validateProjectExists(id))
                                throw new EntityNotFoundException("Project Not Found for ID "+id);

                            return SelectedProject.builder()
                                    .cv(cv)
                                    .selectedProjectId(id)
                                    .build();
                        })
                        .toList()
        );
    }

}
