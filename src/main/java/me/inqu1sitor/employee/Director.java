package me.inqu1sitor.employee;

import me.inqu1sitor.degree.ManagerDegree;
import me.inqu1sitor.department.Department;
import me.inqu1sitor.department.DirectorDepartment;
import me.inqu1sitor.department.HasDepartment;
import me.inqu1sitor.department.UpdatableDepartment;

public class Director extends Manager implements HasDepartment, UpdatableDepartment<DirectorDepartment> {

    private DirectorDepartment department;

    public Director(final String id,
                    final String name,
                    final double grossSalary,
                    final ManagerDegree degree,
                    final DirectorDepartment department) {
        super(id, name, grossSalary, degree);
        setDepartment(department);
    }

    @Override
    public Department getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(final DirectorDepartment department) {
        this.department = department;
    }

    @Override
    public Class<DirectorDepartment> getDepartmentType() {
        return DirectorDepartment.class;
    }

    @Override
    public double getGrossSalary() {
        return super.getGrossSalary() + 5000;
    }

    @Override
    public double getNetSalary() {
        final double grossSalary = getGrossSalary();
        double netSalary;
        if (grossSalary < 30000) {
            netSalary = super.getNetSalary();
        }
        if (grossSalary >= 30000 && grossSalary <= 50000) {
            netSalary = grossSalary * 0.8;
        } else {
            netSalary = grossSalary - 30000 * 0.2 - (grossSalary - 30000) * 0.4;
        }
        return Truncator.truncateTo2(netSalary);
    }

    @Override
    public String toString() {
        return super.toString() + " Dept: " + getDepartment().getName();
    }
}
