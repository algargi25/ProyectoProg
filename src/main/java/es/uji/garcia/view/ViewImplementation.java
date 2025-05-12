package es.uji.garcia.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ViewImplementation {

    // Controles accesibles desde el controlador
    public ComboBox<String> algorithmCombo;
    public ComboBox<String> distanceCombo;
    public ListView<String> songListView;
    public Spinner<Integer> recommendationCountSpinner;
    public Button recommendButton;
    public ListView<String> resultListView;
    public Label recommendationLabel;

    private BorderPane root;

    public void init(Stage primaryStage) {
        // ComboBoxes
        algorithmCombo = new ComboBox<>();
        algorithmCombo.getItems().addAll("kMeans", "KNN");
        algorithmCombo.setValue("kMeans");

        distanceCombo = new ComboBox<>();
        distanceCombo.getItems().addAll("Euclidiana", "Manhattan");
        distanceCombo.setValue("Euclidiana");

        // ListView para las canciones
        songListView = new ListView<>();
        String sep = System.getProperty("file.separator");
        String ruta = "recommender";
        List<String> names;
        try {
            names = readNames(ruta + sep + "songs_test_names.csv");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("No se pudieron leer los nombres de canciones", e);
        }
        ObservableList<String> canciones = FXCollections.observableList(names);
        songListView.getItems().addAll(canciones);

        // Spinner para cantidad de recomendaciones
        recommendationCountSpinner = new Spinner<>(1, 20, 5);

        // Botón para recomendar
        recommendButton = new Button("Recomendar");
        recommendButton.setDisable(true); // Desactivado al inicio

        // Área de resultados
        resultListView = new ListView<>();
        recommendationLabel = new Label("Recomendaciones para:");

        VBox recommendationBox = new VBox(5, recommendationLabel, resultListView);

        // Layout de controles
        VBox controls = new VBox(10,
                new Label("Algoritmo:"), algorithmCombo,
                new Label("Distancia:"), distanceCombo,
                new Label("Selecciona canción:"), songListView,
                new Label("Número de recomendaciones:"), recommendationCountSpinner,
                recommendButton
        );

        root = new BorderPane();
        root.setLeft(controls);
        root.setCenter(recommendationBox);

        // Escena
        Scene scene = new Scene(root, 700, 500);
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