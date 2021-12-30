package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private String lang;

    public Programmer(String name, String surname, String education, String birthday, boolean haveComputer, String lang) {
        super(name, surname, education, birthday, haveComputer);
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public Program makeProgram(TechnicalSpecification spec) {
        Program program = new Program();
        return program;
    }

}
