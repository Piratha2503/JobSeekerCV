package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.ResponsetDTOs;

import com.cvmaker.Jobscorecv.Domains.Candidate.Entities.Profile;

public record ProfileResponse(

        Long id,
        Long userId,
        String fullName,
        String headline,
        String address,
        String email,
        String phone,
        String githubLink,
        String linkedInLink,
        String personalWebLink
) {
    public static ProfileResponse map(Profile profile) {
        return new ProfileResponse(
                profile.getId(),
                profile.getUserId(),
                profile.getFullName(),
                profile.getHeadline(),
                profile.getAddress(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getGithubLink(),
                profile.getLinkedInLink(),
                profile.getPersonalWebLink()
        );
    }
}
