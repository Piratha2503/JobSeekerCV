
package com.cvmaker.Jobscorecv.Domains.Users.Services.Impl;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.PermissionResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.Permission;
import com.cvmaker.Jobscorecv.Domains.Users.Repositories.PermissionRepository;
import com.cvmaker.Jobscorecv.Domains.Users.Services.Mappers.PermissionMapper;
import com.cvmaker.Jobscorecv.Domains.Users.Services.PermissionService;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;

    @Override
    public PermissionResponseDTO create(PermissionRequestDTO dto){
        Permission entity = PermissionMapper.toEntity(dto);
        return PermissionMapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public PermissionResponseDTO getById(Long id){
        Permission entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Permission not found for id:- "+id));
        return PermissionMapper.toResponseDTO(entity);
    }

    @Override
    public PermissionResponseDTO update(Long id, PermissionUpdateDTO dto){
        Permission entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Permission not found for id:- "+id));
        PermissionMapper.applyUpdate(entity,dto);
        return PermissionMapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id){
        Permission entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Permission not found for id:- "+id));
        repository.delete(entity);
    }

    @Override
    public Page<PermissionResponseDTO> getAll(int page,int size,String sortBy,String order){
        Sort sort = order.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return repository.findAll(pageable).map(PermissionMapper::toResponseDTO);
    }
}
