package es.uji.garcia.model.recommender;

import es.uji.garcia.model.algorithms.Algorithm;
import es.uji.garcia.model.data.table.Table;
import es.uji.garcia.model.exceptions.LikedItemNotFoundException;

import java.util.*;

public class RecSys {

    private Algorithm<Table, Integer> algorithm;
    private Map<String, Integer> itemClassMap;
    private List<String> itemNames;

    public RecSys(Algorithm<Table, Integer> algorithm) {
        this.algorithm = algorithm;
        this.itemClassMap = new HashMap<>();
        this.itemNames = new ArrayList<>();
    }

    public void train(Table trainData) throws Exception {
        algorithm.train(trainData);
    }

    public void initialise(Table testData, List<String> testItemNames) {
        this.itemNames = testItemNames;
        itemClassMap.clear();

        for (int i = 0; i < testData.getRowCount(); i++) {
            int cluster = algorithm.estimate(testData.getRowAt(i).getData());
            itemClassMap.put(testItemNames.get(i), cluster);
        }
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) throws LikedItemNotFoundException {

        if(!itemNames.contains(nameLikedItem)){
            throw new LikedItemNotFoundException("No se ha encontrado el elemento", nameLikedItem);
        }

        List<String> recomendaciones = new ArrayList<>();

        if (!itemClassMap.containsKey(nameLikedItem)) {
            throw new IllegalArgumentException("El ítem no existe en la lista de prueba.");
        }

        int likedClass = itemClassMap.get(nameLikedItem);

        for (String item : itemNames) {
            if (!item.equals(nameLikedItem) && itemClassMap.get(item) == likedClass) {
                recomendaciones.add(item);
                if (recomendaciones.size() >= numRecommendations) {
                    break;
                }
            }
        }

        return recomendaciones;
    }
}
