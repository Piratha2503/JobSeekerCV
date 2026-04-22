package com.cvmaker.Jobscorecv.Domains.Candidate.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import com.cvmaker.Jobscorecv.Common.Enums.EmploymentType;
import com.cvmaker.Jobscorecv.Common.Enums.WorkMode;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "experiences",
        indexes = {
                @Index(name = "idx_experiences_profile_id", columnList = "profile_id"),
                @Index(name = "idx_experiences_work_mode", columnList = "work_mode"),
                @Index(name = "idx_experiences_employment_type", columnList = "employment_type"),
                @Index(name = "idx_experiences_location", columnList = "location")}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "job_title", nullable = false, length = 100)
    private String jobTitle;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "location", length = 150)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", nullable = false)
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_mode", nullable = false)
    private WorkMode workMode;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_current", nullable = false)
    private boolean isCurrent = false;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;
}