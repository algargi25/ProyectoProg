package es.uji.garcia.distances;

import java.util.List;

public class ManhattanDistance implements Distance{
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        double distancia = 0.0;
        for (int i = 0; i < p.size(); i++) {
            distancia += Math.abs(p.get(i) - q.get(i));
        }

        return distancia;
    }
}
