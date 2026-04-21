
package com.cvmaker.Jobscorecv.Domains.Users.DTOs.RequestDTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserUpdateDTO {
    private boolean active;
    private List<Long> role_ids_remove;
    private List<Long> role_ids_add;
}
