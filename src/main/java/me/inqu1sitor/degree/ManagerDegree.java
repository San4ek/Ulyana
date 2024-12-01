package me.inqu1sitor.degree;

public enum ManagerDegree implements Degree {

    BSC("BSc", 0.1),
    MSC("MSc", 0.2),
    PHD("PhD", 0.35);

    private final String name;
    private final double bonus;

    ManagerDegree(String name, double bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}
