package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Certification;

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
) {

    public static Certification toEntity(CertificationCreateRequest request){
        return Certification.builder()
                .certificationName(request.certificationName())
                .issuingOrganization(request.issuingOrganization())
                .credentialId(request.credentialId())
                .issuedDate(request.issuedDate())
                .expiryDate(request.expiryDate())
                .doesNotExpire(request.doesNotExpire())
                .orderIndex(request.orderIndex())
                .build();
    }

}