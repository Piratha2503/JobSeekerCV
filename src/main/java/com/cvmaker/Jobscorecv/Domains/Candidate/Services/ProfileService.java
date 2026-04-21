package com.cvmaker.Jobscorecv.Domains.Candidate.Services;

import com.cvmaker.Jobscorecv.Common.APIResponse.APIContentResponse;
import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProfileCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProfileUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ProfileResponse;

import java.util.List;

public interface ProfileService {

    ProfileResponse createProfile(ProfileCreateRequest request);

    ProfileResponse updateProfile(Long id, ProfileUpdateRequest request);

    void deleteProfile(Long id);

    ProfileResponse getProfileById(Long id);

    List<ProfileResponse> getAllProfiles(PaginatedResponse.Pagination pagination);
}
