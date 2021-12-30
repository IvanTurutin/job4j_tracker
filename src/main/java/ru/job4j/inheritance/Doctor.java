package ru.job4j.inheritance;

import java.util.Calendar;

public class Doctor extends Profession {

    private boolean haveGown;

    public Doctor(String name, String surname, String education, String birthday, boolean haveGown) {
        super(name, surname, education, birthday);
        this.haveGown = haveGown;
    }

    public Report makeCheckUp(Patient patient) {
        Report report = new Report();
        return report;
    }

    public boolean getHaveGown() {
        return haveGown;
    }

}
