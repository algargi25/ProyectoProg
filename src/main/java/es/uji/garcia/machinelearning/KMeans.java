package es.uji.garcia.machinelearning;

import es.uji.garcia.table.Table;

import java.util.*;

public class KMeans implements Algorithm<Table> {
    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroides;

    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroides = new ArrayList<>();
    }

    @Override
    public void train(Table datos) {
        if (numClusters > datos.getRowCount()) {
            throw new InvalidClusterNumberException(numClusters, datos.getRowCount());
        }

        Random random = new Random(seed);
        centroides.clear();

        // 1. Inicializar centroides aleatorios
        for (int i = 0; i < numClusters; i++) {
            int randomIndex = random.nextInt(datos.getRowCount());
            centroides.add(new ArrayList<>(datos.getRowAt(randomIndex).getData()));
        }

        // 2. Iterar para ajustar los centroides
        for (int iter = 0; iter < numIterations; iter++) {
            Map<Integer, List<List<Double>>> clusters = new HashMap<>();
            for (int i = 0; i < numClusters; i++) {
                clusters.put(i, new ArrayList<>());
            }

            // 3. Asignar cada punto al centroide más cercano
            for (int i = 0; i < datos.getRowCount(); i++) {
                List<Double> punto = datos.getRowAt(i).getData();
                int clusterIndex = obtenerCentroideMasCercano(punto);
                clusters.get(clusterIndex).add(punto);
            }

            // 4. Recalcular los centroides
            for (int i = 0; i < numClusters; i++) {
                if (!clusters.get(i).isEmpty()) {
                    centroides.set(i, calcularNuevoCentroide(clusters.get(i)));
                }
            }
        }
    }

    @Override
    public Integer estimate(List<Double> datos) {
        if (centroides.isEmpty()) {
            throw new IllegalStateException("El modelo no ha sido entrenado.");
        }

        return obtenerCentroideMasCercano(datos) + 1; // Ajuste para estar en el rango (1, ..., K)
    }

    private int obtenerCentroideMasCercano(List<Double> punto) {
        int indiceMin = 0;
        double minDistancia = distanciaEuclidiana(punto, centroides.get(0));

        for (int i = 1; i < centroides.size(); i++) {
            double distancia = distanciaEuclidiana(punto, centroides.get(i));
            if (distancia < minDistancia) {
                minDistancia = distancia;
                indiceMin = i;
            }
        }
        return indiceMin;
    }

    private double distanciaEuclidiana(List<Double> a, List<Double> b) {
        double suma = 0.0;
        for (int i = 0; i < a.size(); i++) {
            suma += Math.pow(a.get(i) - b.get(i), 2);
        }
        return Math.sqrt(suma);
    }

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