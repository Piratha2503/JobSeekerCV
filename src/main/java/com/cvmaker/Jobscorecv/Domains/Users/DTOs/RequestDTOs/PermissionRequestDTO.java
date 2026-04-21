
package com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PermissionRequestDTO(

        @NotBlank(message = "Permission name is required")
        @Size(min = 3, max = 50, message = "Permission name must be between 3 and 50 characters")
        String name,

        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description

) {}
