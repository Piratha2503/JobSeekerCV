package com.cvmaker.Jobscorecv.Domains.Candidate.Repositories;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillCategoryRepository
        extends JpaRepository<SkillCategory, Long> {

    Optional<SkillCategory> findByName(String name);

    boolean existsByName(String name);
}
