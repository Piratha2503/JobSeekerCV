
package com.cvmaker.Jobscorecv.Domains.Users.Services.Impl;

import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.EntityNotFoundException;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.UserResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.Role;
import com.cvmaker.Jobscorecv.Domains.Users.Entities.User;
import com.cvmaker.Jobscorecv.Domains.Users.Repositories.RoleRepository;
import com.cvmaker.Jobscorecv.Domains.Users.Repositories.UserRepository;
import com.cvmaker.Jobscorecv.Domains.Users.Services.Mappers.UserMapper;
import com.cvmaker.Jobscorecv.Domains.Users.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final RoleRepository role_repository;

    @Override
    public UserResponseDTO create(UserRequestDTO dto){
        User entity = UserMapper.toEntity(dto);

        return UserMapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public UserResponseDTO getById(Long id){
        User entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found for id:- "+id));
        return UserMapper.toResponseDTO(entity);
    }

    @Override
    public UserResponseDTO update(Long id, UserUpdateDTO dto){
        User entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found for id:- "+id));

        entity.setActive(dto.active());

        if (dto.role_ids_remove() != null &&
                !dto.role_ids_remove().isEmpty()){

            Map<Long,Role> roleMap = entity.getRoles()
                    .stream().collect(Collectors.toMap(Role::getId,role -> role));

            dto.role_ids_remove().forEach(roleMap::remove);

            entity.setRoles(new HashSet<>(roleMap.values()));

        }

        if (dto.role_ids_add() != null &&
                !dto.role_ids_add().isEmpty()){

            entity.setRoles(dto.role_ids_add().stream()
                    .map(aLong -> role_repository
                            .findById(aLong)
                            .orElseThrow(()-> new EntityNotFoundException("Role not found for Id:- "+ aLong))
                    )
                    .collect(Collectors.toSet()));
        }

        return UserMapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Override
    public Page<UserResponseDTO> getAll(int page,int size,String sortBy,String order){
        Sort sort = order.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return repository.findAll(pageable).map(UserMapper::toResponseDTO);
    }

}
