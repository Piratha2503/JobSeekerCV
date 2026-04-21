package com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs;

import java.util.List;

public record UserUpdateDTO(
        boolean active,
        List<Long> role_ids_remove,
        List<Long> role_ids_add ) {}