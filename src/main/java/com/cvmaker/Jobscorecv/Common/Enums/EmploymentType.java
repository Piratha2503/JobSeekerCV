package com.cvmaker.Jobscorecv.Common.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmploymentType {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    CONTRACT("Contract"),
    FREELANCE("Freelance"),
    INTERNSHIP("Internship");

    private final String employmentType;

    public static EmploymentType getByType(String type) {
        for (EmploymentType t : values()) {
            if (t.getEmploymentType().equalsIgnoreCase(type)) return t;
        }
        throw new AssertionError(
                "EmploymentType not found [type: " + type + "]"
        );
    }
}
