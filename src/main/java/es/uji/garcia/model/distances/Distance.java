package es.uji.garcia.model.distances;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q);
}
