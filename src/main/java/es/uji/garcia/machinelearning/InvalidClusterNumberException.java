package es.uji.garcia.machinelearning;

public class InvalidClusterNumberException extends IllegalArgumentException {
    private final int numClusters;
    private final int numRows;

    public InvalidClusterNumberException(int numClusters, int numRows) {
        super("Número de clústeres inválido: " + numClusters + " (Debe ser menor o igual que el número de datos: " + numDatos + ")");
        this.numClusters = numClusters;
        this.numRows = numRows;
    }

    public int getNumberOfClusters() {
        return numClusters;
    }

    public int getNumRows() {
        return numRows;
    }
}
