package ru.job4j.inheritance;

public class Surgeon extends Doctor{

    private double scalpelLength;

    public Surgeon(String name, String surname, String education, String birthday, boolean haveGown, double scalpelLength) {
        super(name, surname, education, birthday, haveGown);
        this.scalpelLength = scalpelLength;
    }

    public double getScalpelLength() {
        return scalpelLength;
    }

    public HealthyPatient surgery(Patient patient) {
        HealthyPatient healthyPatient = new HealthyPatient();
        return healthyPatient;
    }
}
