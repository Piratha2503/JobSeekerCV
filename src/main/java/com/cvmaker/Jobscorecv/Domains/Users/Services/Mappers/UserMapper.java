
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
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public static UserResponseDTO toResponseDTO(User entity){
        return new UserResponseDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getRoles().stream().map(Role::getName).toList(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
