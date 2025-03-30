package es.uji.garcia.machinelearning;

import es.uji.garcia.table.TableWithLabels;

import java.util.List;

public interface Algorithm<T> {

    void train(T data);
    Integer estimate(List<Double> data);
}
