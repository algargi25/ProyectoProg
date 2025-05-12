package es.uji.garcia.view;

import es.uji.garcia.controller.MainController;
import es.uji.garcia.model.algorithms.Algorithm;
import es.uji.garcia.model.algorithms.KMeans;
import es.uji.garcia.model.data.reader.CSVLabeledFileReader;
import es.uji.garcia.model.data.table.Table;
import es.uji.garcia.model.distances.Distance;
import es.uji.garcia.model.distances.EuclideanDistance;
import es.uji.garcia.model.exceptions.LikedItemNotFoundException;
import es.uji.garcia.model.recommender.RecSys;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class MyApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        ViewImplementation view = new ViewImplementation();
        view.init(primaryStage);

        MainController controller = new MainController(view);
        controller.init();  // aquí conectas eventos y lógica
    }

    public static void main(String[] args) {
        launch(args);
    }
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws IOException, URISyntaxException {
//        // ComboBoxes
//        ComboBox<String> algorithmCombo = new ComboBox<>();
//        algorithmCombo.getItems().addAll("kMeans", "KNN");
//        algorithmCombo.setValue("kMeans");
//
//        ComboBox<String> distanceCombo = new ComboBox<>();
//        distanceCombo.getItems().addAll("Euclidiana", "Manhattan");
//        distanceCombo.setValue("Euclidiana");
//
//        // ListView para las canciones
//        ListView<String> songListView = new ListView<>();
//        String sep = System.getProperty("file.separator");
//        String ruta = "recommender";
//        List<String> names = readNames(ruta + sep + "songs_test_names.csv");
//        ObservableList<String> canciones = FXCollections.observableList(names);
//        songListView.getItems().addAll(canciones);
//
//        // Spinner para cantidad de recomendaciones
//        Spinner<Integer> recommendationCountSpinner = new Spinner<>(1, 20, 5);
//
//        // Botón para recomendar
//        Button recommendButton = new Button("Recomendar");
//        recommendButton.setDisable(true); // Desactivado al inicio
//
//        // Área para mostrar resultados
//        ListView<String> resultListView = new ListView<>();
//        Label recommendationLabel = new Label("Recomendaciones para:");
//
//        // Layout del centro
//        VBox recommendationBox = new VBox(5, recommendationLabel, resultListView);
//
//        // Actualizar UI según selección
//        songListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
//            recommendButton.setDisable(newVal == null);
//            recommendationLabel.setText("Recomendaciones para: " + (newVal != null ? newVal : ""));
//        });
///// ///////////////////////////////////
//        // Acción del botón
//        recommendButton.setOnAction(e -> {
//            int numRecs = recommendationCountSpinner.getValue();
//            String algorithm = algorithmCombo.getValue();
//            String distance = distanceCombo.getValue();
//            String selectedSong = songListView.getSelectionModel().getSelectedItem();
//            Distance dist = new EuclideanDistance();
//            String separator = System.getProperty("file.separator");
//            Algorithm alg = new KMeans(15, 10, 53, dist);
//            Table trainTable = new CSVLabeledFileReader("recommender" + separator + "/songs_train_withoutnames.csv").readTableFromSource();
//            Table testTable = new CSVLabeledFileReader("recommender" + separator + "/songs_test_withoutnames.csv").readTableFromSource();
//            List<String> testItemNames;
//            try {
//                testItemNames = readNames("recommender" + separator + "/songs_test_names.csv");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            } catch (URISyntaxException ex) {
//                throw new RuntimeException(ex);
//            }
//            RecSys rs = new RecSys(alg);
//            try {
//                rs.train(trainTable);
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//            rs.initialise(testTable, testItemNames);
//            resultListView.getItems().clear();
//            List<String> recomendaciones = null; // Simulado
//            try {
//                recomendaciones = rs.recommend(selectedSong,numRecs);
//            } catch (LikedItemNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
//            resultListView.getItems().addAll(recomendaciones);
//        });
//
//        // Layout de controles
//        VBox controls = new VBox(10,
//                new Label("Algoritmo:"), algorithmCombo,
//                new Label("Distancia:"), distanceCombo,
//                new Label("Selecciona canción:"), songListView,
//                new Label("Número de recomendaciones:"), recommendationCountSpinner,
//                recommendButton
//        );
//
//        BorderPane root = new BorderPane();
//        root.setLeft(controls);
//        root.setCenter(recommendationBox);
//
//        // Mostrar escena
//        Scene scene = new Scene(root, 700, 500);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Sistema de Recomendación de Canciones");
//        primaryStage.show();
//    }
//
//    private List<String> readNames(String fileOfItemNames) throws IOException, URISyntaxException {
//        String path = getClass().getClassLoader().getResource(fileOfItemNames).toURI().getPath();
//
//        List<String> names = new ArrayList<>();
//        Scanner scanner = new Scanner(new File(path));
//        while (scanner.hasNextLine()) {
//            names.add(scanner.nextLine());
//        }
//        scanner.close();
//        return names;
//    }
//    // Función ficticia de recomendación (para pruebas)
//    private List<String> getDummyRecommendations(String baseSong, int count) {
//        List<String> recs = new ArrayList<>();
//        for (int i = 1; i <= count; i++) {
//            recs.add("Recomendación " + i + " para " + baseSong);
//        }
//        return recs;
//    }

}
