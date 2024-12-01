package me.inqu1sitor.employee;

import me.inqu1sitor.salary.HasNetSalary;

import java.util.Objects;

public class Employee implements HasNetSalary {

    private final String id;
    private String name;
    private double grossSalary;

    public Employee(final String id, final String name, final double grossSalary) {
        this.id = id;
        setName(name);
        setGrossSalary(grossSalary);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(final double grossSalary) {
        this.grossSalary = Truncator.truncateTo2(grossSalary);
    }

    @Override
    public double getNetSalary() {
        return Truncator.truncateTo2(getGrossSalary() * 0.9);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Employee employee)) return false;
        return  Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGrossSalary());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getName())
                .append("'s gross salary is ")
                .append(String.format("%.2f", getGrossSalary()))
                .append(" SEK per month.")
                .toString();
    }
}
