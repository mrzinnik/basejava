package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.Resume.ContactType;
import com.basejava.webapp.model.SectionType;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume testResume = new Resume("Григорий Кислин");

        testResume.addContactInfo(ContactType.PHONE, "+7(921) 855-0482");
        testResume.addContactInfo(ContactType.SKYPE, "grigory.kislin");
        testResume.addContactInfo(ContactType.MAIL, "gkislin@yandex.ru");
        testResume.addContactInfo(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        testResume.addContactInfo(ContactType.GITHUB, "https://github.com/gkislin");
        testResume.addContactInfo(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        testResume.addContactInfo(ContactType.HOMEPAGE, "http://gkislin.ru/");

        testResume.addSectionInfo(SectionType.PERSONAL,
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        testResume.addSectionInfo(SectionType.OBJECTIVE,
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        testResume.addSectionInfo(SectionType.ACHIEVEMENT,
                "С 2013 года: разработка проектов \"Разработка Web приложения\", \"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                        "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                        "Более 1000 выпускников.");
        testResume.addSectionInfo(SectionType.ACHIEVEMENT,
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        testResume.addSectionInfo(SectionType.QUALIFICATIONS,
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        testResume.addSectionInfo(SectionType.QUALIFICATIONS,
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce");

        testResume.addSectionInfo(SectionType.EXPERIENCE,
                "Java Online Projects",
                "2013-10",
                "настоящее время",
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        testResume.addSectionInfo(SectionType.EXPERIENCE,
                "Wrike",
                "2014-10",
                "2016-01",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");

        testResume.addSectionInfo(SectionType.EDUCATION,
                "Coursera",
                "2013-03",
                "2013-05",
                "\"Functional Programming Principles in Scala\" by Martin Odersky");
        testResume.addSectionInfo(SectionType.EDUCATION,
                "Luxoft",
                "2011-03",
                "2011-04",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");

        System.out.println(testResume.getFullName());
        System.out.println("----------------------------");
        for (ContactType type : ContactType.values()) {
            System.out.print(type.getTitle() + ": ");
            System.out.println(testResume.getContact(type));
        }
        System.out.println("----------------------------");
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle() + ": ");
            System.out.println(testResume.getSection(type));
            System.out.println("----------------------------");
        }
    }
}
