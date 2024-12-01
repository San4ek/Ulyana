package me.inqu1sitor.department;

public interface UpdatableDepartment<T extends Department> {

    void setDepartment(T department);

    Class<T> getDepartmentType();
}
