package com.cvmaker.Jobscorecv.Domains.CV.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "selected_experiences",
        indexes = {
                @Index(name = "idx_selected_experiences_cv_id", columnList = "cv_id"),
                @Index(name = "idx_selected_experiences_selected_experience_id", columnList = "selected_experience_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id",nullable = false)
    private CV cv;

    private Long selectedExperienceId;
}
