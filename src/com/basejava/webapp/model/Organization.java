package com.basejava.webapp.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Organization implements Section {

    private List<Experience> organizations = new LinkedList<>();

    @Override
    public void addInfo(Experience e) {
        organizations.add(e);
    }

    @Override
    public String toString() {
        Iterator<Experience> it = organizations.listIterator();
        if (!it.hasNext())
            return "No experience";

        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            Experience e = it.next();
            sb.append(e);
            if (it.hasNext()) {
                sb.append("\n\n");
            }
        }
        return sb.toString();
    }
}
