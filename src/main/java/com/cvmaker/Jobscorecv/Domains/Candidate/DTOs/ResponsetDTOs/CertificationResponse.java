package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Certification;

import java.time.LocalDate;

public record CertificationResponse(
        Long id,
        Long profileId,
        String certificationName,
        String issuingOrganization,
        String credentialId,
        LocalDate issuedDate,
        LocalDate expiryDate,
        boolean doesNotExpire,
        Integer orderIndex
) {
    public static CertificationResponse map(Certification entity){
        return new CertificationResponse(
                entity.getId(),
                entity.getProfile().getId(),
                entity.getCertificationName(),
                entity.getIssuingOrganization(),
                entity.getCredentialId(),
                entity.getIssuedDate(),
                entity.getExpiryDate(),
                entity.isDoesNotExpire(),
                entity.getOrderIndex()
        );
    }
}