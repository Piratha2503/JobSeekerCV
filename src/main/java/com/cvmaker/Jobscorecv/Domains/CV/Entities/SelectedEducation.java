package com.cvmaker.Jobscorecv.Domains.CV.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "selected_educations",
        indexes = {
                @Index(name = "idx_selected_educations_cv_id", columnList = "cv_id"),
                @Index(name = "idx_selected_educations_selected_education_id", columnList = "selected_education_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id",nullable = false)
    private CV cv;

    private Long selectedEducationId;
}
