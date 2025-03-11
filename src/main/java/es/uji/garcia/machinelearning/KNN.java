package es.uji.garcia.machinelearning;

import es.uji.garcia.table.RowWithLabel;
import es.uji.garcia.table.TableWithLabels;

import java.util.List;

public class KNN {

    private TableWithLabels trainingData;

    public void train (TableWithLabels data){
        this.trainingData = data;
    }
    public Integer estimate(List<Double> data ){
        if(trainingData == null){
            throw new IllegalStateException("No se encuentra");
        }
        boolean primero = true;
        double distancia_min = 0;
        Integer etiqueta = null;
        for(RowWithLabel it : trainingData.getLabeledRows()){
            double distancia = calculoEuclidiano(data, it.getData());
            if(primero){
                distancia_min = distancia;
                etiqueta = trainingData.getLabelAsInteger(it.getLabel());
                primero = false;
            }
            if(distancia < distancia_min){
                distancia_min = distancia;
                etiqueta = trainingData.getLabelAsInteger(it.getLabel());
            }
        }
        return etiqueta;
    }

    private double calculoEuclidiano(List<Double> sample1, List<Double> sample2) {
        double sum = 0.0;
        for (int i = 0; i < sample1.size(); i++) {
            sum += Math.pow(sample1.get(i) - sample2.get(i), 2);
        }
        return Math.sqrt(sum);
    }
}
