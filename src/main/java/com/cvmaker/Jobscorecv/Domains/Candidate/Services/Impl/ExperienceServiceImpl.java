package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ExperienceUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ExperienceResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Experience;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.ExperienceRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository repository;

    @Override
    public ExperienceResponse createExperience(ExperienceCreateRequest request) {

        log.info("Creating experience for profile {}", request.profileId());

        Experience experience = repository.save(ExperienceCreateRequest.toEntity(request));

        return ExperienceResponse.map(experience);
    }

    @Override
    public ExperienceResponse updateExperience(Long id, ExperienceUpdateRequest request) {

        log.info("Updating experience {}", id);

        Experience experience = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experience not found id:- " + id));

        ExperienceUpdateRequest.updateEntity(experience,request);

        repository.save(experience);

        return ExperienceResponse.map(experience);
    }

    @Override
    public void deleteExperience(Long id) {

        log.info("Deleting experience {}", id);

        Experience experience = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experience not found id:- "+id));

        repository.delete(experience);
    }

    @Override
    public ExperienceResponse getExperienceById(Long id) {

        log.info("Fetching experience {}", id);

        Experience experience = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experience not found id:- "+id));

        return ExperienceResponse.map(experience);
    }

    @Override
    public List<ExperienceResponse> getAllExperiences(PaginatedResponse.Pagination pagination) {

        log.info("Fetching all experiences");

        Sort sortBy = pagination.getOrderedBy().equalsIgnoreCase("DESC")
                ? Sort.by(pagination.getSortedBy()).descending()
                : Sort.by(pagination.getSortedBy()).ascending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sortBy
        );

        Page<Experience> experiences = repository.findAll(pageable);

        pagination.setTotalPages(experiences.getTotalPages());
        pagination.setTotalRecords(experiences.getTotalElements());

        return experiences.stream()
                .map(ExperienceResponse::map)
                .toList();
    }
}