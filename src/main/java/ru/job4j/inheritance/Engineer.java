package ru.job4j.inheritance;

import java.util.Calendar;

public class Engineer extends Profession {

    private boolean haveComputer;

    public Engineer(String name, String surname, String education, String birthday, boolean haveComputer) {
        super(name, surname, education, birthday);
        this.haveComputer = haveComputer;
    }

    public String calculation(TechnicalSpecification spec) {
        return "Calculation results.";
    }

    public boolean getHaveComputer() {
        return haveComputer;
    }
}
