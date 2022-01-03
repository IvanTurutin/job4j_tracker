package ru.job4j.poly;

public class VehicleMain {
    public static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle bus = new Bus();
        Vehicle airplane2 = new Airplane();
        Vehicle train = new Train();
        Vehicle bus2 = new Bus();
        Vehicle train2 = new Train();
        Vehicle[] vehicles = new Vehicle[] {airplane, bus, airplane2, train, bus2, train2, };
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle.getClass().getSimpleName() + " передвигается ");
            vehicle.move();
            System.out.print("Звук двигателя " + vehicle.getClass().getSimpleName() + ": ");
            vehicle.engine();
        }
    }
}
