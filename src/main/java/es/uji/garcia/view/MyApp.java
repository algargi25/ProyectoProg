package es.uji.garcia.view;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, URISyntaxException {
        // ComboBoxes
        ComboBox<String> algorithmCombo = new ComboBox<>();
        algorithmCombo.getItems().addAll("kMeans", "KNN");
        algorithmCombo.setValue("kMeans");

        ComboBox<String> distanceCombo = new ComboBox<>();
        distanceCombo.getItems().addAll("Euclidiana", "Manhattan");
        distanceCombo.setValue("Euclidiana");

        // ListView para las canciones
        ListView<String> songListView = new ListView<>();
        String sep = System.getProperty("file.separator");
        String ruta = "recommender";
        List<String> names = readNames(ruta+sep+ "/songs_test_names.csv");
        ObservableList<String> canciones = FXCollections.observableList(names);

        songListView.getItems().addAll(canciones); // aquí van las canciones reales

        // Spinner para cantidad de recomendaciones
        Spinner<Integer> recommendationCountSpinner = new Spinner<>(1, 20, 5);

        // Botón para recomendar
        Button recommendButton = new Button("Recomendar");
        recommendButton.setDisable(true); // Desactivado al inicio

        // Activar botón solo si se selecciona una canción
        songListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            recommendButton.setDisable(newVal == null);
        });

        // Área para mostrar resultados
        ListView<String> resultListView = new ListView<>();

        // Acción del botón
        recommendButton.setOnAction(e -> {
            String selectedSong = songListView.getSelectionModel().getSelectedItem();
            int numRecs = recommendationCountSpinner.getValue();
            String algorithm = algorithmCombo.getValue();
            String distance = distanceCombo.getValue();

            // Aquí llamas a tu lógica de recomendación
            resultListView.getItems().clear();
            resultListView.getItems().add("Recomendación 1 para " + selectedSong);
            resultListView.getItems().add("Recomendación 2...");
            // etc.
        });

        // Layouts
        VBox controls = new VBox(10,
                new Label("Algoritmo:"), algorithmCombo,
                new Label("Distancia:"), distanceCombo,
                new Label("Selecciona canción:"), songListView,
                new Label("Número de recomendaciones:"), recommendationCountSpinner,
                recommendButton
        );

        BorderPane root = new BorderPane();
        root.setLeft(controls);
        root.setCenter(new VBox(new Label("Recomendaciones:"), resultListView));

        // Mostrar
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Recomendación de Canciones");
        primaryStage.show();
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
