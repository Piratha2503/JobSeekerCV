
package com.cvmaker.Jobscorecv.Domains.Users.Services.Mappers;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.UserResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.Role;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper {


    public static User toEntity(UserRequestDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponseDTO toResponseDTO(User entity){
        return UserResponseDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .roles(entity.getRoles().stream().map(Role::getName).toList())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .updatedAt(entity.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
