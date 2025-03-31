package es.uji.garcia.algorithms;

import java.util.List;

public interface Algorithm<T,R> {

    public void train(T data);
    R estimate(List<Double> data);
}
