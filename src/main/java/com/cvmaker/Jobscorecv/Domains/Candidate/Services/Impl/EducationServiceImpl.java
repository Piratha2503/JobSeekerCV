package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.EducationCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.EducationUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.EducationResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Education;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.EducationRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.EducationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    private final EducationRepository repository;

    @Override
    public EducationResponse createEducation(EducationCreateRequest request) {

        log.info("Creating education for profile {}", request.profileId());

        Education education = repository.save(
                EducationCreateRequest.toEntity(request)
        );

        return EducationResponse.map(education);
    }

    @Override
    public EducationResponse updateEducation(Long id, EducationUpdateRequest request) {

        log.info("Updating education {}", id);

        Education education = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found id:- " + id));

        EducationUpdateRequest.updateEntity(education, request);

        repository.save(education);

        return EducationResponse.map(education);
    }

    @Override
    public void deleteEducation(Long id) {

        log.info("Deleting education {}", id);

        Education education = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found id:- " + id));

        repository.delete(education);
    }

    @Override
    public EducationResponse getEducationById(Long id) {

        log.info("Fetching education {}", id);

        Education education = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found id:- " + id));

        return EducationResponse.map(education);
    }

    @Override
    public List<EducationResponse> getAllEducations(PaginatedResponse.Pagination pagination) {

        Sort sortBy = pagination.getOrderedBy().equalsIgnoreCase("DESC")
                ? Sort.by(pagination.getSortedBy()).descending()
                : Sort.by(pagination.getSortedBy()).ascending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sortBy
        );

        Page<Education> educations = repository.findAll(pageable);

        pagination.setTotalPages(educations.getTotalPages());
        pagination.setTotalRecords(educations.getTotalElements());

        return educations.stream()
                .map(EducationResponse::map)
                .toList();
    }
}
