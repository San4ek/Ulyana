package me.inqu1sitor.degree;

public interface UpdatableDegree<T extends Degree> {

    void setDegree(T degree);

    Class<T> getDegreeType();
}
