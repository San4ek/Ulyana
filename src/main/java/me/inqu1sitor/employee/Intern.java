package me.inqu1sitor.employee;

import me.inqu1sitor.gpa.HasGpa;
import me.inqu1sitor.gpa.UpdatableGpa;

public class Intern extends Employee implements HasGpa, UpdatableGpa {

    private int gpa;

    public Intern(final String id,
                  final String name,
                  final double grossSalary,
                  final int gpa) {
        super(id, name, grossSalary);
        setGpa(gpa);
    }

    @Override
    public int getGpa() {
        return gpa;
    }

    @Override
    public void setGpa(final int gpa) {
        this.gpa = gpa;
    }

    @Override
    public double getGrossSalary() {
        final int gpa = getGpa();
        if (gpa < 5) {
            return 0;
        }
        if (gpa >= 5 && gpa <= 8) {
            return super.getGrossSalary();
        } else {
            return super.getGrossSalary() + 1000;
        }
    }

    @Override
    public double getNetSalary() {
        return getGrossSalary();
    }

    @Override
    public String toString() {
        return super.toString() + ". GPA: " + getGpa();
    }
}
