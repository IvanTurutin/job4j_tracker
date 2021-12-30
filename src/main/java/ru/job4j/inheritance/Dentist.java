package ru.job4j.inheritance;

public class Dentist extends Doctor{

    private String drillBrand;

    public Dentist(String name, String surname, String education, String birthday, boolean haveGown, String drillBrand) {
        super(name, surname, education, birthday, haveGown);
        this.drillBrand = drillBrand;
    }

    public String getDrillBrand() {
        return drillBrand;
    }

    public HealthyTeeth dentalTreatment(Patient patient) {
        HealthyTeeth  healthyTeeth = new HealthyTeeth();
        return healthyTeeth;
    }

}
