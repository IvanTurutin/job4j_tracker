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

    public int sumAllOperation(int y) {
        return sum(y) + minus(y) + this.multiply(y) + this.divide(y);
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
        System.out.println(calc.sumAllOperation(10));
    }
}
