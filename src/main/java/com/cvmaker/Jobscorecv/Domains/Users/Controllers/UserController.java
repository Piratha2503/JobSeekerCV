
package com.cvmaker.Jobscorecv.Domains.Users.Controllers;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserRequestDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs.UserUpdateDTO;
import com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs.UserResponseDTO;
import com.cvmaker.Jobscorecv.Domains.Users.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id,@RequestBody UserUpdateDTO dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public Page<UserResponseDTO> getAll(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @RequestParam(defaultValue="createdAt") String sortBy,
        @RequestParam(defaultValue="DESC") String order
    ){
        return service.getAll(page,size,sortBy,order);
    }
}
