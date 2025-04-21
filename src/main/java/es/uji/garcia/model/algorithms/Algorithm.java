package es.uji.garcia.model.algorithms;

import java.util.List;

public interface Algorithm<T,R> {

    public void train(T data) throws Exception;
    R estimate(List<Double> data);
}
