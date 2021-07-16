package com.example.demo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReportType {
    SPAM("SPAM"),
    IMPOLITE("IMPOLITE"),
    UNKNOWN("UNKNOWN");

    private final String value;

    ReportType(String value) {
        this.value = value;
    }

    public static ReportType getReportType(String type) {
        return Arrays.stream(values())
                .filter(value -> value.value.equalsIgnoreCase(type))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
