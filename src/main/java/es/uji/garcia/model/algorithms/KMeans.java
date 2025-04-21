package es.uji.garcia.model.algorithms;

import es.uji.garcia.model.data.table.Table;
import es.uji.garcia.model.distances.Distance;
import es.uji.garcia.model.exceptions.InvalidClusterNumberException;

import java.util.*;

public class KMeans implements Algorithm<Table, Integer> {
    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroides;
    private Distance distancia;

    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroides = new ArrayList<>();

    }

    public KMeans(int numClusters, int numIterations, long seed, Distance distancia) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroides = new ArrayList<>();
        this.distancia = distancia;
    }


    public void train(Table datos) throws InvalidClusterNumberException{
        if (numClusters > datos.getRowCount()) {
            throw new InvalidClusterNumberException(numClusters, datos.getRowCount());
        }
        Random random = new Random(seed);
        centroides.clear();
        // Centroides aleatorios
        for (int i = 0; i < numClusters; i++) {
            int randomIndex = random.nextInt(datos.getRowCount());
            centroides.add(new ArrayList<>(datos.getRowAt(randomIndex).getData()));
        }
        for (int iter = 0; iter < numIterations; iter++) {
            Map<Integer, List<List<Double>>> clusters = new HashMap<>();
            for (int i = 0; i < numClusters; i++) {
                clusters.put(i, new ArrayList<>());
            }
            for (int i = 0; i < datos.getRowCount(); i++) {
                List<Double> punto = datos.getRowAt(i).getData();
                int clusterIndex = obtenerCentroideMasCercano(punto);
                clusters.get(clusterIndex).add(punto);
            }
            // Recalculo de centroides
            for (int i = 0; i < numClusters; i++) {
                if (!clusters.get(i).isEmpty()) {
                    centroides.set(i, calcularNuevoCentroide(clusters.get(i)));
                }
            }
        }
    }


    public Integer estimate(List<Double> datos) {
        if (centroides.isEmpty()) {
            throw new IllegalStateException();
        }

        return obtenerCentroideMasCercano(datos) + 1;
    }

    private int obtenerCentroideMasCercano(List<Double> punto) {
        int indiceMin = 0;
        double minDistancia = distancia.calculateDistance(punto, centroides.getFirst());

        for (int i = 1; i < centroides.size(); i++) {
            double distancia = this.distancia.calculateDistance(punto, centroides.get(i));
            if (distancia < minDistancia) {
                minDistancia = distancia;
                indiceMin = i;
            }
        }
        return indiceMin;
    }

//    private double distanciaEuclidiana(List<Double> a, List<Double> b) {
//        double suma = 0.0;
//        for (int i = 0; i < a.size(); i++) {
//            suma += Math.pow(a.get(i) - b.get(i), 2);
//        }
//        return Math.sqrt(suma);
//    }

    private List<Double> calcularNuevoCentroide(List<List<Double>> puntos) {
        int dimensiones = puntos.get(0).size();
        List<Double> nuevoCentroide = new ArrayList<>(Collections.nCopies(dimensiones, 0.0));

        for (List<Double> punto : puntos) {
            for (int i = 0; i < dimensiones; i++) {
                nuevoCentroide.set(i, nuevoCentroide.get(i) + punto.get(i));
            }
        }

        for (int i = 0; i < dimensiones; i++) {
            nuevoCentroide.set(i, nuevoCentroide.get(i) / puntos.size());
        }
        return nuevoCentroide;
    }
}