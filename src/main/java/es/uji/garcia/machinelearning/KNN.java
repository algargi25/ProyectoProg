package es.uji.garcia.machinelearning;

import es.uji.garcia.table.RowWithLabel;
import es.uji.garcia.table.TableWithLabels;

import java.util.List;

public class KNN {

    private TableWithLabels trainingData;

    public void train(TableWithLabels data) {
        this.trainingData = data;
    }

    public Integer estimate(List<Double> data) {
        if (trainingData == null) {
            throw new IllegalStateException("No se encuentra");
        }

        double distanciaMin = Double.MAX_VALUE;
        Integer etiqueta = null;

        for (RowWithLabel row : trainingData.getRows()) {
            double distancia = calculoEuclidiano(data, row.getData());

            if (distancia < distanciaMin) {
                distanciaMin = distancia;
                etiqueta = trainingData.getLabelAsInteger(row.getLabel());
            }
        }
        return etiqueta;
    }

    private double calculoEuclidiano(List<Double> a, List<Double> b) {
        double suma = 0.0;
        for (int i = 0; i < a.size(); i++) {
            suma += Math.pow(a.get(i) - b.get(i), 2);
        }
        return Math.sqrt(suma);
    }
}
