package com.cvmaker.Jobscorecv.Domains.Candidate.Repositories;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}