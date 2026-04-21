
package com.cvmaker.Jobscorecv.Domains.Users.Services;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.RoleResponseDTO;
import org.springframework.data.domain.Page;

public interface RoleService {
    RoleResponseDTO create(RoleRequestDTO dto);
    RoleResponseDTO getById(Long id);
    RoleResponseDTO update(Long id, RoleUpdateDTO dto);
    void delete(Long id);
    Page<RoleResponseDTO> getAll(int page,int size,String sortBy,String order);
}
