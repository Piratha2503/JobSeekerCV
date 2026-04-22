package com.cvmaker.Jobscorecv.Domains.Candidate.Repositories;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    boolean existsByEmail(@Email String email);

    boolean existsByPhone(@Size(max = 20) String phone);

    boolean existsByEmailAndIdNot(@Email String email, Long id);

    boolean existsByPhoneAndIdNot(@Size(max = 20) String phone, Long id);
}