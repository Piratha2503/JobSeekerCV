package com.cvmaker.Jobscorecv.Domains.Candidate.DTOs.RequestDTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SkillCategoryCreateRequest(

        @NotBlank(message = "Skill category name is required")
        @Size(max = 100, message = "Skill category name must not exceed 100 characters")
        String name,

        @NotNull(message = "Order index is required")
        Integer orderIndex

) {}
