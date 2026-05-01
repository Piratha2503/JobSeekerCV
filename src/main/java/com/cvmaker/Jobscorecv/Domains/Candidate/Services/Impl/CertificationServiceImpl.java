package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.CertificationCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.CertificationUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.CertificationResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Certification;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Profile;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.CertificationRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.ProfileRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository repository;
    private final ProfileRepository profileRepository;

    @Override
    public CertificationResponse createCertification(CertificationCreateRequest request) {

        Profile profile = profileRepository.getReferenceById(request.profileId());

        Certification entity = Certification.builder()
                .profile(profile)
                .certificationName(request.certificationName())
                .issuingOrganization(request.issuingOrganization())
                .credentialId(request.credentialId())
                .issuedDate(request.issuedDate())
                .expiryDate(request.expiryDate())
                .doesNotExpire(request.doesNotExpire())
                .orderIndex(request.orderIndex())
                .build();

        return CertificationResponse.map(repository.save(entity));
    }

    @Override
    public CertificationResponse updateCertification(Long id, CertificationUpdateRequest request) {

        Certification entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        entity.setCertificationName(request.certificationName());
        entity.setIssuingOrganization(request.issuingOrganization());
        entity.setCredentialId(request.credentialId());
        entity.setIssuedDate(request.issuedDate());
        entity.setExpiryDate(request.expiryDate());
        entity.setDoesNotExpire(request.doesNotExpire());
        entity.setOrderIndex(request.orderIndex());

        return CertificationResponse.map(repository.save(entity));
    }

    @Override
    public void deleteCertification(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CertificationResponse getCertificationById(Long id) {
        Certification entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        return CertificationResponse.map(entity);
    }

    @Override
    public List<CertificationResponse> getAllCertifications(PaginatedResponse.Pagination pagination) {

        Sort sort = pagination.getOrderedBy().equalsIgnoreCase("asc") ?
                Sort.by(pagination.getSortedBy()).ascending() :
                Sort.by(pagination.getSortedBy()).descending();

        Pageable pageable = PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                sort
        );

        Page<Certification> page = repository.findAll(pageable);

        pagination.setTotalPages(page.getTotalPages());
        pagination.setTotalRecords(page.getTotalElements());

        return page.getContent()
                .stream()
                .map(CertificationResponse::map)
                .toList();
    }
}