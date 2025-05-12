package es.uji.garcia.controller;

import es.uji.garcia.model.algorithms.Algorithm;
import es.uji.garcia.model.algorithms.KMeans;
import es.uji.garcia.model.algorithms.KNN;
import es.uji.garcia.model.data.reader.CSVLabeledFileReader;
import es.uji.garcia.model.data.table.Table;
import es.uji.garcia.model.distances.Distance;
import es.uji.garcia.model.distances.EuclideanDistance;
import es.uji.garcia.model.distances.ManhattanDistance;
import es.uji.garcia.model.exceptions.LikedItemNotFoundException;
import es.uji.garcia.model.recommender.RecSys;
import es.uji.garcia.view.ViewImplementation;
import javafx.beans.value.ObservableValue;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainController {

    private final ViewImplementation view;

    public MainController(ViewImplementation view) {
        this.view = view;
    }

    public void init() {
        // Actualizar UI según selección
        view.songListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obs, String oldVal, String newVal) -> {
            view.recommendButton.setDisable(newVal == null);
            view.recommendationLabel.setText("Recomendaciones para: " + (newVal != null ? newVal : ""));
        });

        view.recommendButton.setOnAction(e -> {
            int numRecs = view.recommendationCountSpinner.getValue();
            String algorithm = view.algorithmCombo.getValue();
            String distance = view.distanceCombo.getValue();
            String selectedSong = view.songListView.getSelectionModel().getSelectedItem();
            String sep = System.getProperty("file.separator");

            // Elegir distancia
            Distance dist = distance.equals("Euclidiana") ? new EuclideanDistance() : new ManhattanDistance();

            // Elegir algoritmo
            Algorithm alg = algorithm.equals("KNN")
                    ? new KNN(dist)
                    : new KMeans(15, 10, 53, dist);

            // Leer tablas y nombres
            Table trainTable = new CSVLabeledFileReader("recommender" + sep + "songs_train_withoutnames.csv").readTableFromSource();
            Table testTable = new CSVLabeledFileReader("recommender" + sep + "songs_test_withoutnames.csv").readTableFromSource();
            List<String> testItemNames;
            try {
                testItemNames = readNames("recommender" + sep + "songs_test_names.csv");
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }

            // Inicializar sistema de recomendación
            RecSys rs = new RecSys(alg);
            try {
                rs.train(trainTable);
                rs.initialise(testTable, testItemNames);
            } catch (Exception ex) {
                throw new RuntimeException("Error durante el entrenamiento o inicialización", ex);
            }

            // Recomendar
            List<String> recomendaciones;
            try {
                recomendaciones = rs.recommend(selectedSong, numRecs);
            } catch (LikedItemNotFoundException ex) {
                throw new RuntimeException("Canción no encontrada en el conjunto de prueba", ex);
            }

            // Mostrar resultados
            view.resultListView.getItems().clear();
            view.resultListView.getItems().addAll(recomendaciones);
        });
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
