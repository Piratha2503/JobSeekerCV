package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SkillCreateRequest(

        @NotBlank(message = "Skill name is required")
        @Size(max = 100, message = "Skill name must not exceed 100 characters")
        String skillName,

        @NotNull(message = "Category id is required")
        Long categoryId

) {}
