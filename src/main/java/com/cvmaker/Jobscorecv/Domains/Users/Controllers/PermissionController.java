
package com.cvmaker.Jobscorecv.Domains.Users.Controllers;

import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.PermissionUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.PermissionResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService service;

    @PostMapping
    public PermissionResponseDTO create(@RequestBody PermissionRequestDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PermissionResponseDTO update(@PathVariable Long id,@RequestBody PermissionUpdateDTO dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public PermissionResponseDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public Page<PermissionResponseDTO> getAll(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @RequestParam(defaultValue="createdAt") String sortBy,
        @RequestParam(defaultValue="DESC") String order
    ){
        return service.getAll(page,size,sortBy,order);
    }
}
