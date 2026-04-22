package com.cvmaker.Jobscorecv.Domains.Candidate.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "skills",
        indexes = {
                @Index(name = "idx_skills_category_id",
                        columnList = "category_id"),
                @Index(name = "idx_skills_name",
                        columnList = "skill_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", nullable = false,
            unique = true, length = 100)
    private String skillName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private SkillCategory category;
}