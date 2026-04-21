package com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequestDTO(

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotEmpty(message = "At least one role must be assigned")
        List<Long> role_ids

) {}