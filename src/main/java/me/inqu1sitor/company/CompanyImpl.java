package me.inqu1sitor.company;

import me.inqu1sitor.degree.Degree;
import me.inqu1sitor.degree.HasDegree;
import me.inqu1sitor.degree.UpdatableDegree;
import me.inqu1sitor.department.Department;
import me.inqu1sitor.department.UpdatableDepartment;
import me.inqu1sitor.employee.Employee;
import me.inqu1sitor.gpa.UpdatableGpa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class CompanyImpl implements Company {

    private final Map<String, Employee> employeeMap = new HashMap<>();

    @Override
    public double getNetSalary(String id) {
        return retrieveEmployee(id).getNetSalary();
    }

    @Override
    public String printEmployee(String id) {
        return retrieveEmployee(id).toString();
    }

    @Override
    public String createEmployee(String id, String name, double grossSalary) {
        final Employee employee = employeeMap.putIfAbsent(id, new Employee(id, name, grossSalary));
        if (employee == null) {
            return String.format("Employee %s was registered successfully.", id);
        }
        throw new IllegalArgumentException(String.format("Cannot createEmployee. ID %s is already registered", id));
    }

    @Override
    public Employee removeEmployee(String id) {
        final Employee employee = employeeMap.remove(id);
        if (employee == null) {
            throw new IllegalArgumentException(String.format("Employee %s was not registered yet", id));
        }
        System.out.printf("Employee %s was successfully removed\n", id);
        return employee;
    }

    @Override
    public Employee retrieveEmployee(String id) {
        final Employee employee = employeeMap.get(id);
        if (employee == null) {
            throw new IllegalArgumentException(String.format("Employee %s was not registered yet", id));
        }
        return employee;
    }

    @Override
    public Employee updateName(String id, String name) {
        final Employee employee = retrieveEmployee(id);
        employee.setName(name);
        showUpdatedSuccessfully(id);
        return employee;
    }

    @Override
    public Employee updateSalary(String id, double salary) {
        final Employee employee = retrieveEmployee(id);
        employee.setGrossSalary(salary);
        showUpdatedSuccessfully(id);
        return employee;
    }

    @Override
    public <T extends Degree> Employee updateDegree(String id, T degree) {
        final Employee employee = retrieveEmployee(id);
        if (employee instanceof UpdatableDegree<?> degreableEmployee) {
            if (degreableEmployee.getDegreeType().isInstance(degree)) {
                ((UpdatableDegree<T>) degreableEmployee).setDegree(degree);
                showUpdatedSuccessfully(id);
                return employee;
            } else {
                throw new IllegalArgumentException(String.format("Employee %s doesn't support such degree type", id));
            }
        }
        throw new IllegalArgumentException(String.format("Employee %s doesn't have degree", id));
    }

    @Override
    public <T extends Department> Employee updateDepartment(String id, T department) {
        final Employee employee = retrieveEmployee(id);
        if (employee instanceof UpdatableDepartment<?> departableEmployee) {
            if (departableEmployee.getDepartmentType().isInstance(department)) {
                ((UpdatableDepartment<T>) departableEmployee).setDepartment(department);
                showUpdatedSuccessfully(id);
                return employee;
            } else {
                throw new IllegalArgumentException(String.format("Employee %s doesn't support such department type", id));
            }
        }
        throw new IllegalArgumentException(String.format("Employee %s doesn't have department", id));
    }

    @Override
    public Employee updateGpa(String id, int gpa) {
        final Employee employee = retrieveEmployee(id);
        if (employee instanceof UpdatableGpa gpableEmployee) {
            gpableEmployee.setGpa(gpa);
            showUpdatedSuccessfully(id);
            return employee;
        }
        throw new IllegalArgumentException(String.format("Employee %s doesn't have gpa", id));
    }

    @Override
    public double getTotalGrossSalary() {
        return calcSalaries(Employee::getGrossSalary);
    }

    @Override
    public double getTotalNetSalary() {
        return calcSalaries(Employee::getNetSalary);
    }

    @Override
    public void mapEachDegree() {
        checkIsAnyExists();
        System.out.println("Academic background of employees: ");
        employeeMap.values().stream()
                .filter(employee -> employee instanceof HasDegree)
                .collect(Collectors.groupingBy(employee -> ((HasDegree) employee).getDegree()))
                .forEach((key, value) -> {
                    final String ids = value.stream()
                            .map(Employee::getId)
                            .collect(Collectors.joining(", "));
                    if (!ids.isEmpty()) {
                        System.out.printf("%s : => %s\n", key.getName(), ids);
                    }
                });
    }

    @Override
    public void printSortedEmployees() {
        checkIsAnyExists();
        System.out.println("Employees sorted by gross salary (ascending order):");
        employeeMap.values().stream()
                .sorted(Comparator.comparingDouble(Employee::getGrossSalary))
                .forEach(System.out::println);
    }

    @Override
    public void printAllEmployees() {
        checkIsAnyExists();
        System.out.println("All registered employees:");
        employeeMap.values().forEach(System.out::println);
    }

    private double calcSalaries(final ToDoubleFunction<Employee> salaries) {
        checkIsAnyExists();
        return employeeMap.values().stream().mapToDouble(salaries).sum();
    }

    private void showUpdatedSuccessfully(final String id) {
        System.out.printf("Employee %s was updated successfully\n", id);
    }

    private void checkIsAnyExists() {
        if (employeeMap.isEmpty()) {
            throw new IllegalArgumentException("No employees registered yet.");
        }
    }
}
