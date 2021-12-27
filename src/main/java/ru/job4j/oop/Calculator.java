package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int z) {
        return z - x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int divide(int b) {
        return b / x;
    }

    public int sumAll(int y, int z, int a, int b) {
        return sum(y) + minus(z) + this.multiply(a) + this.divide(b);
    }

    public static void main(String[] args) {
        int sum = Calculator.sum(10);
        int minus = Calculator.minus(5);
        Calculator calc = new Calculator();
        int multiply = calc.multiply(2);
        int divide = calc.divide(15);
        System.out.println(sum);
        System.out.println(minus);
        System.out.println(divide);
        System.out.println(multiply);
        System.out.println(calc.sumAll(10,5,2,15));
    }
}
