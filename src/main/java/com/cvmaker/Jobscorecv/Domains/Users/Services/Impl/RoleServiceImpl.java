
package com.cvmaker.Jobscorecv.Domains.Users.Services.Impl;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.RoleResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.Role;
import com.cvmaker.Jobscorecv.Domains.Users.Repositories.RoleRepository;
import com.cvmaker.Jobscorecv.Domains.Users.Services.Mappers.RoleMapper;
import com.cvmaker.Jobscorecv.Domains.Users.Services.RoleService;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public RoleResponseDTO create(RoleRequestDTO dto){
        Role entity = RoleMapper.toEntity(dto);
        return RoleMapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public RoleResponseDTO getById(Long id){
        Role entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Role not found for id:- "+id));
        return RoleMapper.toResponseDTO(entity);
    }

    @Override
    public RoleResponseDTO update(Long id, RoleUpdateDTO dto){
        Role entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Role not found for id:- "+id));
        RoleMapper.applyUpdate(entity,dto);
        return RoleMapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id){
        Role entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Role not found for id:- "+id));
        repository.delete(entity);
    }

    @Override
    public Page<RoleResponseDTO> getAll(int page,int size,String sortBy,String order){
        Sort sort = order.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return repository.findAll(pageable).map(RoleMapper::toResponseDTO);
    }
}
