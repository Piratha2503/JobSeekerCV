package com.cvmaker.Jobscorecv.Domains.Candidate.Services.Impl;

import com.cvmaker.Jobscorecv.Common.APIResponse.PaginatedResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProfileCreateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs.ProfileUpdateRequest;
import com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs.ProfileResponse;
import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Profile;
import com.cvmaker.Jobscorecv.Domains.Candidate.Repositories.ProfileRepository;
import com.cvmaker.Jobscorecv.Domains.Candidate.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileResponse createProfile(ProfileCreateRequest request) {

        log.info("Creating profile for userId: {}", request.userId());

        if(profileRepository.existsByUserId(request.userId()))
            throw new RuntimeException("Profile already exists for user");

        Profile profile = Profile.builder()
                .userId(request.userId())
                .fullName(request.fullName())
                .headline(request.headline())
                .address(request.address())
                .email(request.email())
                .phone(request.phone())
                .githubLink(request.githubLink())
                .linkedInLink(request.linkedInLink())
                .personalWebLink(request.personalWebLink())
                .build();

        profileRepository.save(profile);

        return ProfileResponse.map(profile);
    }

    @Override
    public ProfileResponse updateProfile(Long id, ProfileUpdateRequest request) {

        log.info("Updating profile id {}", id);

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setFullName(request.fullName());
        profile.setHeadline(request.headline());
        profile.setAddress(request.address());
        profile.setEmail(request.email());
        profile.setPhone(request.phone());
        profile.setGithubLink(request.githubLink());
        profile.setLinkedInLink(request.linkedInLink());
        profile.setPersonalWebLink(request.personalWebLink());

        profileRepository.save(profile);

        return ProfileResponse.map(profile);
    }

    @Override
    public void deleteProfile(Long id) {

        log.info("Deleting profile {}", id);

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profileRepository.delete(profile);
    }

    @Override
    public ProfileResponse getProfileById(Long id) {

        log.info("Fetching profile {}", id);

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return ProfileResponse.map(profile);
    }

    @Override
    public List<ProfileResponse> getAllProfiles(PaginatedResponse.Pagination pagination) {

        log.info("Fetching all profiles page {} size {}", pagination.getPageNumber(), pagination.getPageSize());

        Sort sortBy = pagination.getOrderedBy().equalsIgnoreCase("DESC")
                ? Sort.by(pagination.getSortedBy()).descending()
                : Sort.by(pagination.getSortedBy()).ascending();

        Pageable pageable = PageRequest.of(pagination.getPageNumber(),pagination.getPageSize(),sortBy);

        Page<Profile> profiles = profileRepository.findAll(pageable);

        pagination.setTotalPages(profiles.getTotalPages());
        pagination.setTotalRecords(profiles.getTotalElements());

        return profiles
                .stream()
                .map(ProfileResponse::map)
                .toList();

    }

}
