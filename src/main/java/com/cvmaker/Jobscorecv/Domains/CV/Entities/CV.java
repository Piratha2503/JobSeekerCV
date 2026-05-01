package com.cvmaker.Jobscorecv.Domains.CV.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "cvs",
        indexes = {
                @Index(name = "idx_educations_profile_id", columnList = "profile_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CV extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long profileId;

    @Column(name = "target_role", length = 150)
    private String targetRole;

    @Column(name = "target_industry", length = 150)
    private String targetIndustry;

    @Enumerated(EnumType.STRING)
    @Column(name = "template_type", nullable = false)
    private TemplateType templateType;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic = false;

    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedExperience> selectedExperiences;
    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedEducation> selectedEducations;
    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedCertification> selectedCertifications;
    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedSkill> selectedSkills;
    @OneToMany(mappedBy = "cv",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedProject> selectedProjects;

    public enum TemplateType {
        CLASSIC,
        MODERN,
        MINIMAL
    }
}
