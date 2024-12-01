package me.inqu1sitor.employee;

import me.inqu1sitor.degree.Degree;
import me.inqu1sitor.degree.HasDegree;
import me.inqu1sitor.degree.ManagerDegree;
import me.inqu1sitor.degree.UpdatableDegree;

public class Manager extends Employee implements HasDegree, UpdatableDegree<ManagerDegree> {

    private ManagerDegree degree;

    public Manager(final String id,
                   final String name,
                   final double grossSalary,
                   final ManagerDegree degree) {
        super(id, name, grossSalary);
        setDegree(degree);
    }

    @Override
    public Degree getDegree() {
        return degree;
    }

    @Override
    public void setDegree(final ManagerDegree degree) {
        this.degree = degree;
    }

    @Override
    public Class<ManagerDegree> getDegreeType() {
        return ManagerDegree.class;
    }

    @Override
    public double getGrossSalary() {
        return Truncator.truncateTo2(super.getGrossSalary() * degree.getBonus());
    }

    @Override
    public String toString() {
        return degree.getName() + super.toString();
    }
}
