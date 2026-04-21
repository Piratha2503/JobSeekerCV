
package com.cvmaker.Jobscorecv.Domains.Users.Services.Mappers;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.RoleResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequestDTO dto){
        return Role.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static RoleResponseDTO toResponseDTO(Role entity){
        return RoleResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .updatedAt(entity.getUpdatedAt().toLocalDateTime())
                .build();
    }

    public static void applyUpdate(Role entity, RoleUpdateDTO dto){
        if(dto.getName()!=null) entity.setName(dto.getName());
        if(dto.getDescription()!=null) entity.setDescription(dto.getDescription());
    }
}
