package es.uji.garcia;

import es.uji.garcia.model.algorithms.Algorithm;
import es.uji.garcia.model.algorithms.KNN;
import es.uji.garcia.model.algorithms.KMeans;
import es.uji.garcia.model.data.CSV;
import es.uji.garcia.model.data.table.Table;
import es.uji.garcia.model.distances.Distance;
import es.uji.garcia.model.distances.EuclideanDistance;
import es.uji.garcia.model.recommender.RecSys;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

class SongRecSys {
    private RecSys recsys;

    SongRecSys(String method) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "recommender";

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+ "/songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+ "/songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+ "/songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+ "/songs_test_withoutnames.csv");

        // Algorithms
        Map<String, Algorithm> algorithms = new HashMap<>();
        Distance dist = new EuclideanDistance();
        algorithms.put("knn",new KNN(dist));
        algorithms.put("kmeans",new KMeans(15, 200, 4321,dist));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
//        CSV csv = null;
//        for (String stage : stages) {
//            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
//            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
//        }

        // Names of items
        List<String> names = readNames(ruta+sep+ "/songs_test_names.csv");

        // Start the RecSys
        this.recsys = new RecSys(algorithms.get(method));
        this.recsys.train(tables.get(method+"train"));
        this.recsys.initialise(tables.get(method+"test"), names);

        // Given a liked item, ask for a number of recomendations
        String liked_name = "Lootkemia";
        List<String> recommended_items = this.recsys.recommend(liked_name,5);

        // Display the recommendation text (to be replaced with graphical display with JavaFX implementation)
        reportRecommendation(liked_name,recommended_items);
    }

    private List<String> readNames(String fileOfItemNames) throws IOException, URISyntaxException {
        String path = getClass().getClassLoader().getResource(fileOfItemNames).toURI().getPath();

        List<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner.close();
        return names;
    }

    private void reportRecommendation(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name);
        }
    }

    public static void main(String[] args) throws Exception {
        new SongRecSys("knn");
        new SongRecSys("kmeans");
    }
}