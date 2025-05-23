package es.uji.garcia.model.algorithms;

import es.uji.garcia.model.data.table.RowWithLabel;
import es.uji.garcia.model.data.table.TableWithLabels;
import es.uji.garcia.model.distances.Distance;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer> {

    private TableWithLabels trainingData;
    private Distance distancia;

    
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

            double dist = this.distancia.calculateDistance(data, row.getData());
            if(primero){
                distanciaMin = dist;
                etiqueta = trainingData.getLabelAsInteger(row.getLabel());
                primero = false;
            }
            if (!primero && dist < distanciaMin) {
                distanciaMin = dist;
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
