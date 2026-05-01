package com.cvmaker.Jobscorecv.Domains.Candidate.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "profiles",
        indexes = {
                @Index(name = "idx_profiles_user_id", columnList = "user_id"),
                @Index(name = "idx_profiles_email", columnList = "email"),
                @Index(name = "idx_profiles_phone", columnList = "phone")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "headline")
    private String headline;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "linked_in_link")
    private String linkedInLink;

    @Column(name = "personal_web_link")
    private String personalWebLink;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "profile_skills",
            joinColumns = @JoinColumn(
                    name = "profile_id",
                    foreignKey = @ForeignKey(
                            foreignKeyDefinition =
                                    "FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE CASCADE"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "skill_id",
                    foreignKey = @ForeignKey(
                            foreignKeyDefinition =
                                    "FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE"
                    )
            )
    )
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Education> educations = new HashSet<>();

    @OneToMany(mappedBy = "certification",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Certification> certifications = new HashSet<>();

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();
}