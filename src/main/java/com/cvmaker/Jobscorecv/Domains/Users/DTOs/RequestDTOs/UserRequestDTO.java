
package com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserRequestDTO {
    private String email;
    private String password;
    private List<Long> role_ids;
}
