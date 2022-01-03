package ru.job4j.poly;

public class Bus implements Transport, Vehicle {
    private int passangers;

    @Override
    public void drive() {
        System.out.println("Автобус поехал");
    }

    @Override
    public void passanger(int passangers) {
        this.passangers = passangers;
    }

    @Override
    public double refuel(int gasoline) {
        double price = 5.4;
        return gasoline * price;
    }

    @Override
    public void move() {
        System.out.println("по дороге.");
    }

    @Override
    public void engine() {
        System.out.println("тр-тр-тр.");
    }
}
