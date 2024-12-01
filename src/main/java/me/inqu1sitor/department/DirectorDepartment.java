package me.inqu1sitor.department;

import java.util.Enumeration;

public enum DirectorDepartment implements Department {

    HUMAN_RESOURCES("Human resources"),
    TECHNICAL("Technical"),
    BUSINESS("Business");

    private final String name;

    DirectorDepartment(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
