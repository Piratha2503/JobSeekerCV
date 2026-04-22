package com.cvmaker.Jobscorecv.Domains.Candidate.Controllers;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProfileCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProfileUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ProfileResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<APIContentResponse<ProfileResponse>> createProfile(
            @Valid @RequestBody ProfileCreateRequest request) {

        log.info("API create profile called");
        ProfileResponse profileResponse =  profileService.createProfile(request);

        return ResponseEntity.status(HttpStatus.OK).body(new APIContentResponse<>(
                "201",
                ResponseStatus.SUCCESS.getStatus(),
                "Profile created successfully",
                "profile",
                profileResponse
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIContentResponse<ProfileResponse>> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileUpdateRequest request) {

        log.info("API update profile {}", id);
        ProfileResponse profileResponse =  profileService.updateProfile(id, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIContentResponse<>(
                "200",
                ResponseStatus.SUCCESS.getStatus(),
                "Profile updated successfully",
                "profile",
                profileResponse
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIContentResponse<Long>> deleteProfile(@PathVariable Long id) {

        log.info("API delete profile {}", id);

        profileService.deleteProfile(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIContentResponse<>(
                "200",
                ResponseStatus.SUCCESS.getStatus(),
                "Profile deleted",
                "profileId",
                id
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIContentResponse<ProfileResponse>> getProfileById(@PathVariable Long id) {

        log.info("API get profile {}", id);
        ProfileResponse response = profileService.getProfileById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new APIContentResponse<>(
                "200",
                ResponseStatus.SUCCESS.getStatus(),
                "Profile fetched",
                "profile",
                response));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<List<ProfileResponse>>> getAllProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sort
            ) {

        log.info("API get all profiles");

        PaginatedResponse.Pagination pagination = PaginatedResponse.Pagination.builder()
                .pageNumber(page)
                .pageSize(size)
                .orderedBy(order)
                .sortedBy(sort)
                .build();

        List<ProfileResponse> profileResponses = profileService.getAllProfiles(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PaginatedResponse<>(
                String.valueOf(HttpStatus.OK.value()),
                ResponseStatus.SUCCESS.getStatus(),
                "Profiles fetched successfully",
                "profiles",
                profileResponses,
                pagination
        ));
    }
}
