package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.CertificationCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.CertificationUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.CertificationResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.CertificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/certifications")
@RequiredArgsConstructor
@Slf4j
public class CertificationController {

    private final CertificationService service;

    @PostMapping
    public ResponseEntity<APIContentResponse<CertificationResponse>> create(
            @RequestBody CertificationCreateRequest request) {

        CertificationResponse response = service.createCertification(request);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "201","SUCCESS","Certification created",
                        "certification",response
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<CertificationResponse>> update(
            @PathVariable Long id,
            @RequestBody CertificationUpdateRequest request) {

        CertificationResponse response = service.updateCertification(id, request);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "200","SUCCESS","Certification updated",
                        "certification",response
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> delete(@PathVariable Long id) {

        service.deleteCertification(id);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "200","SUCCESS","Certification deleted",
                        "certificationId",id
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<CertificationResponse>> getById(@PathVariable Long id) {

        CertificationResponse response = service.getCertificationById(id);

        return ResponseEntity.ok(
                new APIContentResponse<>(
                        "200","SUCCESS","Certification fetched",
                        "certification",response
                ));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<CertificationResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort) {

        PaginatedResponse.Pagination pagination =
                PaginatedResponse.Pagination.builder()
                        .pageNumber(page)
                        .pageSize(size)
                        .orderedBy(order)
                        .sortedBy(sort)
                        .build();

        List<CertificationResponse> responses =
                service.getAllCertifications(pagination);

        return ResponseEntity.ok(
                new PaginatedResponse<>(
                        "200","SUCCESS","Certifications fetched",
                        "certifications",responses,pagination
                ));
    }
}