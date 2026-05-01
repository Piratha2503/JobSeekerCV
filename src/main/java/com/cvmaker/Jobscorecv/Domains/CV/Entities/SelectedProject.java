package com.cvmaker.Jobscorecv.Domains.CV.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "selected_projects",
        indexes = {
                @Index(name = "idx_selected_projects_cv_id", columnList = "cv_id"),
                @Index(name = "idx_selected_projects_selected_project_id", columnList = "selected_project_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id",nullable = false)
    private CV cv;

    private Long selectedProjectId;
}
