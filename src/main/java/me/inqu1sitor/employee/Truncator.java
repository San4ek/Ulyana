package me.inqu1sitor.employee;

public class Truncator {

    public static double truncateTo2(final double value) {
        return Math.floor(value * 100) / 100.0;
    }
}
