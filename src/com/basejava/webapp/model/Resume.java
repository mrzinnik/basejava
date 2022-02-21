package com.basejava.webapp.model;

import java.time.YearMonth;
import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;

        for (ContactType type : ContactType.values()) {
            contacts.put(type, null);
        }

        for (SectionType type : SectionType.values()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    sections.put(type, new SingleTextSection());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    sections.put(type, new ListTextSection());
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    sections.put(type, new Organization());
                    break;
            }
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void addContactInfo(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public String getSection(SectionType type) {
        return sections.get(type).toString();
    }

    public void addSectionInfo(SectionType type, String description) {
        Experience e = new Experience(description);
        sections.get(type).addInfo(e);
    }

    public void addSectionInfo(SectionType type, String organization, String dateFrom, String dateTo, String description) {
        Experience e = new Experience(organization, dateFrom, dateTo, description);
        sections.get(type).addInfo(e);
    }

    public void addSectionInfo(SectionType type, String organization, String dateFrom, String dateTo, String position, String description) {
        Experience e = new Experience(organization, dateFrom, dateTo, position, description);
        sections.get(type).addInfo(e);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + " (" + fullName + ")";
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }

    public enum ContactType {

        PHONE("Телефон"),
        SKYPE("Skype"),
        MAIL("Почта"),
        LINKEDIN("Профиль LinkedIn"),
        GITHUB("Профиль GitHub"),
        STACKOVERFLOW("Профиль Stackoverflow"),
        HOMEPAGE("Домашняя страница");

        private String title;

        ContactType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    private class Experience {

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

    private interface Section {

        void addInfo(Experience info);
    }

    private class SingleTextSection implements Section {

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

    private class ListTextSection implements Section {

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

    private class Organization implements Section {

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
}