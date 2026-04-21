
package com.cvmaker.Jobscorecv.Domains.Users.DTOs.ResponsetDTOs;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String email;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDTO(Long id,
                           String email,
                           List<String> roles,
                           Timestamp createdAt,
                           Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.createdAt = createdAt.toLocalDateTime();
        this.updatedAt = updatedAt.toLocalDateTime();
    }
}
