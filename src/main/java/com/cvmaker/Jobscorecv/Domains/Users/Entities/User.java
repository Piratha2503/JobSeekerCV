package com.cvmaker.Jobscorecv.Domains.Users.Entities;

import com.cvmaker.Jobscorecv.Common.CommonUtils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_users_email", columnList = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends DateTimeUtils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_map",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Plan plan = Plan.FREE;

//    @Getter
//    @AllArgsConstructor
//    public enum Plan {
//        FREE("Free"),
//        PRO("Pro"),
//        PREMIUM("Premium");
//
//        private final String plan;
//
//        public static Plan getByPlan(String plan) {
//            for (Plan p : values()) {
//                if (p.getPlan().equalsIgnoreCase(plan)) return p;
//            }
//            throw new AssertionError(
//                    "Plan not found for given value [plan: " + plan + "]"
//            );
//       }
 //   }

}

