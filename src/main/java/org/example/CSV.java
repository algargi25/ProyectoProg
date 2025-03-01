package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class CSV {


    public CSV() {
    }

    public static Table readTable(String valor) throws URISyntaxException, IOException {
        String fichero = Objects.requireNonNull(CSV.class.getClassLoader().getResource(valor)).toURI().getPath();
        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String line;

        // Leer la primera línea como cabecera
        String[] headers = br.readLine().split(",");

        // Crear la tabla con los encabezados
        Table table = new Table(Arrays.asList(headers));

        // Leer cada fila del archivo y agregarla a la tabla
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            List<Double> rowValues = new ArrayList<>();

            // Convertir los valores a Double
            for (String value : values) {
                rowValues.add(Double.parseDouble(value));
            }
            Row fila = new Row(rowValues);
            // Agregar la fila a la tabla
            table.addRow(fila);
        }

        br.close();
        return table;
    }
    public TableWithLabels readTableWithLabels(String s){
        return null;
    }

}
