package ru.job4j.inheritance;

public class Builder extends Engineer {

    private boolean haveRuler;

    public Builder(String name, String surname, String education, String birthday, boolean haveComputer, boolean haveRuler) {
        super(name, surname, education, birthday, haveComputer);
        this.haveRuler = haveRuler;
    }

    public Drawings makeDrawings(House house) {
        Drawings drw = new Drawings();
        return drw;
    }

    public boolean getHaveRuler() {
        return haveRuler;
    }

}
