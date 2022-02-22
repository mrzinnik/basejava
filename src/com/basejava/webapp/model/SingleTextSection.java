package com.basejava.webapp.model;

public class SingleTextSection implements Section {

    private String description = "No description";

    @Override
    public void addInfo(Experience e) {
        this.description = e.getDescription();
    }

    @Override
    public String toString() {
        return description;
    }
}
