package com.cvmaker.Jobscorecv.Domains.CV.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "selected_certifications",
        indexes = {
                @Index(name = "idx_selected_certifications_cv_id", columnList = "cv_id"),
                @Index(name = "idx_selected_certifications_selected_certification_id", columnList = "selected_certification_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id",nullable = false)
    private CV cv;

    private Long selectedCertificationId;
}
