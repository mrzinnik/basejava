package com.basejava.webapp.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListTextSection implements Section {

    private List<String> descriptionList = new LinkedList<>();

    @Override
    public void addInfo(Experience e) {
        descriptionList.add(e.getDescription());
    }

    @Override
    public String toString() {
        Iterator<String> it = descriptionList.listIterator();
        if (!it.hasNext())
            return "No description";

        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append("* ");
            String e = it.next();
            sb.append(e);
            if (it.hasNext()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
