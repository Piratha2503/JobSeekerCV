package com.cvmaker.Jobscorecv.Domains.CV.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "selected_skills",
        indexes = {
                @Index(name = "idx_selected_skills_cv_id", columnList = "cv_id"),
                @Index(name = "idx_selected_skills_selected_skill_id", columnList = "selected_skill_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedSkill extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id",nullable = false)
    private CV cv;

    private Long selectedSkillId;

}
