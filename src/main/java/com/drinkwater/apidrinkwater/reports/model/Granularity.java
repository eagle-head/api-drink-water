package com.drinkwater.apidrinkwater.reports.model;

import lombok.Getter;

@Getter
public enum Granularity {

    DAILY("daily"),
    WEEKLY("weekly");

    private final String value;

    Granularity(String value) {
        this.value = value;
    }

    public static Granularity fromValue(String value) {
        for (Granularity granularity : Granularity.values()) {
            if (granularity.getValue().equalsIgnoreCase(value)) {
                return granularity;
            }
        }

        throw new IllegalArgumentException("Invalid value for Granularity: " + value);
    }
}
