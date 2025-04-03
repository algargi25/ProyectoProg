package es.uji.garcia.algorithms;

import es.uji.garcia.data.table.RowWithLabel;
import es.uji.garcia.data.table.TableWithLabels;
import es.uji.garcia.distances.Distance;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer> {

    private TableWithLabels trainingData;
    private Distance distancia;

    public KNN() {
    }
    public KNN(Distance distancia) {
        this.distancia = distancia;
    }

    public void train(TableWithLabels data) {
        this.trainingData = data;
    }


    public Integer estimate(List<Double> data) {
        if (trainingData == null) {
            throw new IllegalStateException("No se encuentra");
        }

        double distanciaMin = Double.MAX_VALUE;
        Integer etiqueta = null;
        boolean primero = true;
        for (RowWithLabel row : trainingData.getRows()) {
            double distancia = this.distancia.calculateDistance(data, row.getData());
            if(primero){
                distanciaMin = distancia;
                etiqueta = trainingData.getLabelAsInteger(row.getLabel());
                primero = false;
            }
            if (!primero && distancia < distanciaMin) {
                distanciaMin = distancia;
                etiqueta = trainingData.getLabelAsInteger(row.getLabel());
            }
        }
        return etiqueta;
    }

//    private double calculoEuclidiano(List<Double> a, List<Double> b) {
//        double suma = 0.0;
//        for (int i = 0; i < a.size(); i++) {
//            suma += Math.pow(a.get(i) - b.get(i), 2);
//        }
//        return Math.sqrt(suma);
//    }
}
