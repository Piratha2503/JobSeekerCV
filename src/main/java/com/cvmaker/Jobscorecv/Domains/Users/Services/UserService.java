
package com.cvmaker.Jobscorecv.Domains.Users.Services;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.UserResponseDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponseDTO create(UserRequestDTO dto);
    UserResponseDTO getById(Long id);
    UserResponseDTO update(Long id, UserUpdateDTO dto);
    void delete(Long id);
    Page<UserResponseDTO> getAll(int page,int size,String sortBy,String order);
}
