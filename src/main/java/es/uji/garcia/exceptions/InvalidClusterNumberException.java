package es.uji.garcia.exceptions;

public class InvalidClusterNumberException extends Exception {

    public InvalidClusterNumberException(int clusters, int numClusters) {
        super("Número de clusters (" + clusters + ") no puede ser mayor que el número de datos (" + numClusters + ").");
    }

}
