package es.uji.garcia.recommender;

import es.uji.garcia.algorithms.Algorithm;
import es.uji.garcia.data.table.Table;
import es.uji.garcia.exceptions.InvalidClusterNumberException;
import es.uji.garcia.exceptions.LikedItemNotFoundException;

import java.util.*;

public class RecSys {

    private Algorithm<Table, Integer> algorithm;
    private List<String> testItemNames;
    private Map<String, Integer> estimatedClasses;

    public RecSys(Algorithm<Table, Integer> algorithm) {
        this.algorithm = algorithm;
        this.estimatedClasses = new HashMap<>();
    }

    public void train(Table trainData) throws Exception {

        algorithm.train(trainData);
    }

    public void initialise(Table testData, List<String> testItemNames) {
        this.testItemNames = testItemNames;
        for (int i = 0; i < testData.getRowCount(); i++) {
            estimatedClasses.put(testItemNames.get(i), algorithm.estimate(testData.getRowAt(i).getData()));
        }
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) throws LikedItemNotFoundException {
        if (!estimatedClasses.containsKey(nameLikedItem)) {
            throw new LikedItemNotFoundException("El ítem '" + nameLikedItem + "' no se encuentra en la lista.");
        }

        int targetClass = estimatedClasses.get(nameLikedItem);
        List<String> recommendations = new ArrayList<>();

        for (String item : estimatedClasses.keySet()) {
            if (estimatedClasses.get(item) == targetClass && !item.equals(nameLikedItem)) {
                recommendations.add(item);
                if (recommendations.size() == numRecommendations) break;
            }
        }
        if (recommendations.isEmpty()) {
            throw new LikedItemNotFoundException("No se encontraron recomendaciones para el ítem '" + nameLikedItem + "'.");
        }
        return recommendations;
    }
}
