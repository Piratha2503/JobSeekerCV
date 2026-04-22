package com.cvmaker.Jobscorecv.Domains.Candidate.Repositories;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    boolean existsBySkillName(String skillName);

}
