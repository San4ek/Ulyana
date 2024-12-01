package me.inqu1sitor.company;

import me.inqu1sitor.degree.Degree;
import me.inqu1sitor.department.Department;
import me.inqu1sitor.employee.Employee;

public interface Company {

    double getNetSalary(String id);

    String printEmployee(String id);

    String createEmployee(String id, String name, double grossSalary);

    Employee removeEmployee(String id);

    Employee retrieveEmployee(String id);

    Employee updateName(String id, String name);

    Employee updateSalary(String id, double salary);

    <T extends Degree> Employee updateDegree(String id, T degree);

    <T extends Department> Employee updateDepartment(String id, T department);

    Employee updateGpa(String id, int gpa);

    double getTotalGrossSalary();

    double getTotalNetSalary();

    void mapEachDegree();

    void printSortedEmployees();

    void printAllEmployees();
}
