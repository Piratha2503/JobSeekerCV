package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import jakarta.validation.constraints.*;

public record ProfileCreateRequest(

        @NotNull
        Long userId,

        @NotBlank
        @Size(max = 100)
        String fullName,

        String headline,

        String address,

        @Email
        String email,

        @Size(max = 20)
        String phone,

        String githubLink,

        String linkedInLink,

        String personalWebLink
) {}