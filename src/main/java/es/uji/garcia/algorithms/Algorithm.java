package es.uji.garcia.algorithms;

import es.uji.garcia.exceptions.InvalidClusterNumberException;

import java.util.List;

public interface Algorithm<T,R> {

    public void train(T data) throws Exception;
    R estimate(List<Double> data);
}
