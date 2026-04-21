
package com.cvmaker.Jobscorecv.Domains.Users.Services.Mappers;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.PermissionResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.Permission;

public class PermissionMapper {

    public static Permission toEntity(PermissionRequestDTO dto){
        return Permission.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static PermissionResponseDTO toResponseDTO(Permission entity){
        return PermissionResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .updatedAt(entity.getUpdatedAt().toLocalDateTime())
                .build();
    }

    public static void applyUpdate(Permission entity, PermissionUpdateDTO dto){
        if(dto.name()!=null) entity.setName(dto.name());
        if(dto.description()!=null) entity.setDescription(dto.description());

    }
}
