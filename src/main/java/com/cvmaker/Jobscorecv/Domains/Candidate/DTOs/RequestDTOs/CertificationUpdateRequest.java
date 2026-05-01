package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Certification;

import java.time.LocalDate;

public record CertificationUpdateRequest(
        String certificationName,
        String issuingOrganization,
        String credentialId,
        LocalDate issuedDate,
        LocalDate expiryDate,
        boolean doesNotExpire,
        Integer orderIndex
) {
    public static void updateEntity(Certification entity, CertificationUpdateRequest request){

        if(request.certificationName() != null){
            entity.setCertificationName(request.certificationName());
        }

        if(request.issuingOrganization() != null){
            entity.setIssuingOrganization(request.issuingOrganization());
        }

        if(request.credentialId() != null){
            entity.setCredentialId(request.credentialId());
        }

        if(request.issuedDate() != null){
            entity.setIssuedDate(request.issuedDate());
        }

        if(request.expiryDate() != null){
            entity.setExpiryDate(request.expiryDate());
        }

        // boolean careful ⚠️
        // primitive boolean use பண்ணினா null check முடியாது
        // so better → Boolean use பண்ணு DTO ல
        if(request.doesNotExpire()){
            entity.setDoesNotExpire(true);
        }

        if(request.orderIndex() != null){
            entity.setOrderIndex(request.orderIndex());
        }
    }
}