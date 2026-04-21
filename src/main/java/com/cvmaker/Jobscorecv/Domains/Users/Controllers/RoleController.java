
package com.cvmaker.Jobscorecv.Domains.Users.Controllers;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.RoleUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.RoleResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @PostMapping
    public RoleResponseDTO create(@RequestBody RoleRequestDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public RoleResponseDTO update(@PathVariable Long id,@RequestBody RoleUpdateDTO dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public RoleResponseDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public Page<RoleResponseDTO> getAll(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @RequestParam(defaultValue="createdAt") String sortBy,
        @RequestParam(defaultValue="DESC") String order
    ){
        return service.getAll(page,size,sortBy,order);
    }
}
