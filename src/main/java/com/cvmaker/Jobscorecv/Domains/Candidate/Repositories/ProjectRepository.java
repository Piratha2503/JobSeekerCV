package com.cvmaker.Jobscorecv.Domains.Candidate.Repositories;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
