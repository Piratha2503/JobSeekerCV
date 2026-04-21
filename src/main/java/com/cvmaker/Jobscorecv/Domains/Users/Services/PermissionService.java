
package com.cvmaker.Jobscorecv.Domains.Users.Services;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.PermissionResponseDTO;
import org.springframework.data.domain.Page;

public interface PermissionService {
    PermissionResponseDTO create(PermissionRequestDTO dto);
    PermissionResponseDTO getById(Long id);
    PermissionResponseDTO update(Long id, PermissionUpdateDTO dto);
    void delete(Long id);
    Page<PermissionResponseDTO> getAll(int page,int size,String sortBy,String order);
}
