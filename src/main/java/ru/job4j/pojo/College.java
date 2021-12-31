package ru.job4j.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class College {
    public static void main(String[] args) {
        Student vasya = new Student();
        vasya.setName("Vasiliy");
        vasya.setSurname("Pupkin");
        vasya.setPatronymic("Ivanovich");
        vasya.setGroup("105");
        vasya.setDateOfAdmission(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println(vasya.getName());
        System.out.println(vasya.getSurname());
        System.out.println(vasya.getPatronymic());
        System.out.println(vasya.getGroup());
        System.out.println(vasya.getDateOfAdmission());

    }
}
