package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import java.time.LocalDate;

public record CertificationCreateRequest(
        Long profileId,
        String certificationName,
        String issuingOrganization,
        String credentialId,
        LocalDate issuedDate,
        LocalDate expiryDate,
        boolean doesNotExpire,
        Integer orderIndex
) {}