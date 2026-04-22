package com.cvmaker.Jobscorecv.Common.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkMode {
    ONSITE("Onsite"),
    REMOTE("Remote"),
    HYBRID("Hybrid");

    private final String workMode;

    public static WorkMode getByMode(String mode) {
        for (WorkMode m : values()) {
            if (m.getWorkMode().equalsIgnoreCase(mode)) return m;
        }
        throw new AssertionError(
                "WorkMode not found [mode: " + mode + "]"
        );
    }
}