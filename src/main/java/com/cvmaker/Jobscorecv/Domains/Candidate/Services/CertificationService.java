package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.CertificationCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.CertificationUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.CertificationResponse;

import java.util.List;

public interface CertificationService {

    CertificationResponse createCertification(CertificationCreateRequest request);

    CertificationResponse updateCertification(Long id, CertificationUpdateRequest request);

    void deleteCertification(Long id);

    CertificationResponse getCertificationById(Long id);

    List<CertificationResponse> getAllCertifications(PaginatedResponse.Pagination pagination);
}