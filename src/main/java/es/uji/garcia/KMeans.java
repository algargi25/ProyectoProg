package es.uji.garcia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {

    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroides;

    public KMeans(int numClusters, int numIterations, long seed){
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroides = new ArrayList<>();
    }

    public void train (Table datos){
        Random random = new Random(seed);
    }
    public Integer estimate(List<Double>datos){
        return 2;
    }
}
