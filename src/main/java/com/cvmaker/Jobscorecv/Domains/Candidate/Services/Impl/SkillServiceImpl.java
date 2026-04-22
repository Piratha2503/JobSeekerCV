package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.SkillUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.SkillResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Skill;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.SkillCategory;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.SkillCategoryRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.SkillRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    @Override
    public SkillResponse createSkill(SkillCreateRequest request) {

        log.info("Creating skill {}", request.skillName());

        if(skillRepository.existsBySkillName(request.skillName()))
            throw new RuntimeException("Skill already exists");

        SkillCategory category = skillCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found"));

        Skill skill = Skill.builder()
                .skillName(request.skillName())
                .category(category)
                .build();

        skillRepository.save(skill);

        return SkillResponse.map(skill);
    }

    @Override
    public SkillResponse updateSkill(Long id, SkillUpdateRequest request) {

        log.info("Updating skill {}", id);

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found id:- " + id));

        SkillCategory category = skillCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found"));

        skill.setSkillName(request.skillName());
        skill.setCategory(category);

        skillRepository.save(skill);

        return SkillResponse.map(skill);
    }

    @Override
    public void deleteSkill(Long id) {

        log.info("Deleting skill {}", id);

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found id:- " + id));

        skillRepository.delete(skill);
    }

    @Override
    public SkillResponse getSkillById(Long id) {

        log.info("Fetching skill {}", id);

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found id:- " + id));

        return SkillResponse.map(skill);
    }

    @Override
    public List<SkillResponse> getAllSkills(PaginatedResponse.Pagination pagination) {

        log.info("Fetching all skills");

        Sort sortBy = pagination.getOrderedBy().equalsIgnoreCase("DESC")
                ? Sort.by(pagination.getSortedBy()).descending()
                : Sort.by(pagination.getSortedBy()).ascending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sortBy
        );

        Page<Skill> skills = skillRepository.findAll(pageable);

        pagination.setTotalPages(skills.getTotalPages());
        pagination.setTotalRecords(skills.getTotalElements());

        return skills.stream()
                .map(SkillResponse::map)
                .toList();
    }
}
