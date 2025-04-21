package es.uji.garcia.model.exceptions;

public class InvalidClusterNumberException extends Exception {
    private int numClusters;
    public InvalidClusterNumberException(int clusters, int numClusters) {
        super("Número de clusters (" + clusters + ") no puede ser mayor que el número de datos (" + numClusters + ").");
        this.numClusters = numClusters;
    }

    public int getNumberOfClusters() {
        return numClusters;
    }
}
