package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCategoryCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCategoryUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.SkillCategoryResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.SkillCategory;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.SkillCategoryRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.SkillCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository repository;

    @Override
    public SkillCategoryResponse createSkillCategory(SkillCategoryCreateRequest request) {

        log.info("Creating skill category {}", request.name());

        if(repository.existsByName(request.name()))
            throw new RuntimeException("Skill category already exists");

        SkillCategory category = SkillCategory.builder()
                .name(request.name())
                .orderIndex(request.orderIndex())
                .build();

        repository.save(category);

        return SkillCategoryResponse.map(category);
    }

    @Override
    public SkillCategoryResponse updateSkillCategory(Long id, SkillCategoryUpdateRequest request) {

        log.info("Updating skill category {}", id);

        SkillCategory category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found id:- " + id));

        category.setName(request.name());
        category.setOrderIndex(request.orderIndex());

        repository.save(category);

        return SkillCategoryResponse.map(category);
    }

    @Override
    public void deleteSkillCategory(Long id) {

        log.info("Deleting skill category {}", id);

        SkillCategory category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found id:- " + id));

        repository.delete(category);
    }

    @Override
    public SkillCategoryResponse getSkillCategoryById(Long id) {

        log.info("Fetching skill category {}", id);

        SkillCategory category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found id:- " + id));

        return SkillCategoryResponse.map(category);
    }

    @Override
    public List<SkillCategoryResponse> getAllSkillCategories(PaginatedResponse.Pagination pagination) {

        log.info("Fetching all skill categories");

        Sort sortBy = pagination.getOrderedBy().equalsIgnoreCase("DESC")
                ? Sort.by(pagination.getSortedBy()).descending()
                : Sort.by(pagination.getSortedBy()).ascending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sortBy
        );

        Page<SkillCategory> categories = repository.findAll(pageable);

        pagination.setTotalPages(categories.getTotalPages());
        pagination.setTotalRecords(categories.getTotalElements());

        return categories.stream()
                .map(SkillCategoryResponse::map)
                .toList();
    }
}
