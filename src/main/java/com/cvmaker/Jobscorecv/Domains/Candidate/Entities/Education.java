package com.cvmaker.Jobscorecv.Domains.Candidate.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "educations",
        indexes = {
                @Index(name = "idx_educations_profile_id", columnList = "profile_id"),
                @Index(name = "idx_educations_order_index", columnList = "order_index")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "degree", nullable = false, length = 150)
    private String degree;

    @Column(name = "field_of_study", nullable = false, length = 150)
    private String fieldOfStudy;

    @Column(name = "institution_name", nullable = false, length = 150)
    private String institutionName;

    @Column(name = "location", length = 150)
    private String location;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_current", nullable = false)
    private boolean isCurrent = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type")
    private GradeType gradeType;

    @Column(name = "grade_value", length = 20)
    private String gradeValue;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Getter
    @AllArgsConstructor
    public enum GradeType {
        CGPA("CGPA"),
        PERCENTAGE("Percentage"),
        GRADE("Grade");

        private final String gradeType;

        public static GradeType getByType(String type) {
            for (GradeType g : values()) {
                if (g.getGradeType().equalsIgnoreCase(type)) return g;
            }
            throw new AssertionError(
                    "GradeType not found [type: " + type + "]"
            );
        }
    }
}
