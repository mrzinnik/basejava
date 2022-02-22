package com.basejava.webapp.model;

import java.time.YearMonth;

public class Experience {

    private final String organization;
    private final YearMonth dateFrom;
    private final YearMonth dateTo;
    private final String position;
    private final String description;

    public Experience(String description) {
        this(null, null, null, null, description);
    }

    public Experience(String organization, String dateFrom, String dateTo, String description) {
        this(organization, dateFrom, dateTo, null, description);
    }

    public Experience(String organization, String dateFrom, String dateTo, String position, String description) {
        this.organization = organization;
        this.dateFrom = dateFrom == null ? null : YearMonth.parse(dateFrom);
        this.dateTo = (dateTo == null || "настоящее время".equals(dateTo)) ? null : YearMonth.parse(dateTo);
        this.position = position;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return organization + "\n" +
                dateFrom + " - " + (dateTo != null ? dateTo : "настоящее время") + "\n" +
                (position != null ? (position + "\n") : "") +
                description;
    }
}
