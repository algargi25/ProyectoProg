// TODO: Remplazar <nombre> por el nombre de tu paquete
package es.uji.garcia.recommender;

// TODO: Pon los imports especificos a tu proyecto

import es.uji.garcia.model.algorithms.Algorithm;
import es.uji.garcia.model.algorithms.KMeans;
import es.uji.garcia.model.algorithms.KNN;
import es.uji.garcia.model.data.reader.CSVLabeledFileReader;
import es.uji.garcia.model.data.table.Table;
import es.uji.garcia.model.distances.Distance;
import es.uji.garcia.model.distances.EuclideanDistance;
import es.uji.garcia.model.exceptions.LikedItemNotFoundException;
import es.uji.garcia.model.recommender.RecSys;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {

    private String separator = System.getProperty("file.separator");
    // TODO: cambiar ruta si hace falta
    private String songsFolder = "recommender";

    private RecSys recSys;
    private Algorithm algorithm;

    private Table trainTable;
    private Table testTable;
    private List<String> testItemNames;

    private int numRecommendations = 5;

    @AfterEach
    void tearDown() {
        algorithm = null;
        recSys = null;
        trainTable = null;
        testTable = null;
        testItemNames = null;
    }

    @Nested
    class KNNRecSys {

        @BeforeEach
        // TODO: añadir o eliminar excepciones según tu implementación
        void setUp() throws Exception {

            trainTable = new CSVLabeledFileReader(songsFolder + separator + "/songs_train.csv").readTableFromSource();
            testTable = new CSVLabeledFileReader(songsFolder + separator + "/songs_test.csv").readTableFromSource();
            testItemNames = readNames(songsFolder + separator + "/songs_test_names.csv");

            algorithm = new KNN(new EuclideanDistance());
            recSys = new RecSys(algorithm);
            recSys.train(trainTable);
            recSys.initialise(testTable, testItemNames);
        }

        @Test
        @DisplayName("RecSys[KNN] - estimate")
        void estimate() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("The Weekend", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("The Weekend"));
        }

        @Test
        @DisplayName("RecSys[KNN] - estimate: liked item not returned")
        void estimate_likedItemNotInRecommendation() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("Inside Out", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("Inside Out"));
        }

        @Test
        @DisplayName("RecSys[KNN] - estimate: liked item not found")
        void estimate_likedItemNotFound() {
            assertThrows(LikedItemNotFoundException.class, () -> recSys.recommend("Inside Ouuuut", numRecommendations));
        }
    }

    @Nested
    class KMeansRecSys {

        private int numClusters = 15;
        private int numIterations = 10;
        private long seed = 53;

        @BeforeEach
        // TODO: añadir o eliminar excepciones según tu implementación
        void setUp() throws Exception {
            trainTable = new CSVLabeledFileReader(songsFolder + separator + "/songs_train_withoutnames.csv").readTableFromSource();
            testTable = new CSVLabeledFileReader(songsFolder + separator + "/songs_test_withoutnames.csv").readTableFromSource();
            testItemNames = readNames(songsFolder + separator + "/songs_test_names.csv");
            Distance dist = new EuclideanDistance();
            algorithm = new KMeans(numClusters, numIterations, seed, dist);
            recSys = new RecSys(algorithm);
            recSys.train(trainTable);
            recSys.initialise(testTable, testItemNames);
        }

        @Test
        @DisplayName("RecSys[KMeans] - estimate")
        void estimate() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("The Weekend", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("The Weekend"));
        }

        @Test
        @DisplayName("RecSys[KMeans] - estimate: liked item not returned")
        void estimate_likedItemNotInRecommendation() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("Inside Out", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("Inside Out"));
        }

        @Test
        @DisplayName("RecSys[KMeans] - estimate: liked item not found")
        void estimate_likedItemNotFound() {
            assertThrows(LikedItemNotFoundException.class, () -> recSys.recommend("Inside Ouuuut", numRecommendations));
        }
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
}