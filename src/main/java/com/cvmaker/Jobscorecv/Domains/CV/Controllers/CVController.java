package com.cvmaker.Jobscorecv.Domains.CV.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVCreateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.RequestDTOs.CVUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.CV.DTOs.ResponsetDTOs.CVResponse;
import com.cvmaker.Jobscorecv.Domains.CV.Services.CVService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cvs")
@RequiredArgsConstructor
@Slf4j
public class CVController {

    private final CVService service;

    @PostMapping
    public ResponseEntity<APIContentResponse<CVResponse>> createCV(
            @RequestBody CVCreateRequest request) {

        log.info("API create CV for profile {}", request.profileId());

        CVResponse response = service.create(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "201",
                        ResponseStatus.SUCCESS.getStatus(),
                        "CV created successfully",
                        "cv",
                        response
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<CVResponse>> updateCV(
            @PathVariable Long id,
            @RequestBody CVUpdateRequest request) {

        log.info("API update CV {}", id);

        CVResponse response = service.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "CV updated successfully",
                        "cv",
                        response
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteCV(@PathVariable Long id) {

        log.info("API delete CV {}", id);

        service.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "CV deleted successfully",
                        "cvId",
                        id
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<CVResponse>> getCVById(@PathVariable Long id) {

        log.info("API get CV {}", id);

        CVResponse response = service.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new APIContentResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "CV fetched successfully",
                        "cv",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<CVResponse>>> getAllCVs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort) {

        log.info("API get all CVs page {} size {}", page, size);

        PaginatedResponse.Pagination pagination =
                PaginatedResponse.Pagination.builder()
                        .pageNumber(page)
                        .pageSize(size)
                        .orderedBy(order)
                        .sortedBy(sort)
                        .build();

        List<CVResponse> responses = service.getAll(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginatedResponse<>(
                        "200",
                        ResponseStatus.SUCCESS.getStatus(),
                        "CVs fetched successfully",
                        "cvs",
                        responses,
                        pagination
                ));
    }
}
