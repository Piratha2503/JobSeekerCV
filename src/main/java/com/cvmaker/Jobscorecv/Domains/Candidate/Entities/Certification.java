package com.cvmaker.Jobscorecv.Domains.Candidate.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "certifications",
        indexes = {
                @Index(name = "idx_certifications_profile_id", columnList = "profile_id"),
                @Index(name = "idx_certifications_order_index", columnList = "order_index")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "certification_name", nullable = false, length = 150)
    private String certificationName;

    @Column(name = "issuing_organization", nullable = false, length = 150)
    private String issuingOrganization;

    @Column(name = "credential_id", length = 100)
    private String credentialId;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "does_not_expire", nullable = false)
    private boolean doesNotExpire = false;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;
}
