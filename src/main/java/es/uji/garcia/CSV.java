package es.uji.garcia;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class CSV {


    public CSV() {
    }

//    public static Table readTable(String valor) throws URISyntaxException, IOException {
//        String fichero = Objects.requireNonNull(CSV.class.getClassLoader().getResource(valor)).toURI().getPath();
//        BufferedReader br = new BufferedReader(new FileReader(fichero));
//        String line;
//
//        // Leer la primera línea como cabecera
//        String[] headers = br.readLine().split(",");
//
//        // Crear la tabla con los encabezados
//        Table table = new Table(Arrays.asList(headers));
//
//        // Leer cada fila del archivo y agregarla a la tabla
//        while ((line = br.readLine()) != null) {
//            String[] values = line.split(",");
//            List<Double> rowValues = new ArrayList<>();
//
//            // Convertir los valores a Double
//            for (String value : values) {
//                rowValues.add(Double.parseDouble(value));
//            }
//            Row fila = new Row(rowValues);
//            // Agregar la fila a la tabla
//            table.addRow(fila);
//        }
//
//        br.close();
//        return table;
//    }
//    public TableWithLabels readTableWithLabels(String s) throws IOException {
//        String fichero = route(s);
//        BufferedReader br = new BufferedReader(new FileReader(fichero));
//        String line;
//
//        String[] headers = br.readLine().split(",");
//
//        TableWithLabels table = new TableWithLabels(Arrays.asList(headers));
//
//        while ((line = br.readLine()) != null) {
//            String[] values = line.split(",");
//            List<Double> rowValues = new ArrayList<>();
//
//            for (String value : values) {
//                rowValues.add(Double.parseDouble(value));
//            }
//            Row fila = new Row(rowValues);
//
//            table.addRow(fila);
//        }
//
//        br.close();
//        return table;
//
//    }
public static Table readTable(String fileName) throws IOException {
    String rutaFich = route(fileName);
    List<String> cabecera = new ArrayList<>();
    List<Row> data = new ArrayList<>();

    try (Scanner sc = new Scanner(new File(rutaFich))) {
        boolean primeraLinea = true;

        while (sc.hasNextLine()) {
            String[] valores = sc.nextLine().split(",");

            if (primeraLinea) {
                cabecera.addAll(List.of(valores));
                primeraLinea = false;
            } else {
                List<Double> datos = new ArrayList<>();
                for (String valor : valores) {
                    datos.add(Double.parseDouble(valor));
                }
                data.add(new Row(datos));
            }
        }
    }

    return new Table(cabecera, data);
}
    public TableWithLabels readTableWithLabels(String fileName) throws IOException {
        String rutaFich = route(fileName);
        List<String> cabecera = new ArrayList<>();
        List<RowWithLabel> data = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(rutaFich))) {
            boolean primeraLinea = true;

            while (sc.hasNextLine()) {
                String[] valores = sc.nextLine().split(",");

                if (primeraLinea) {
                    for (int i = 0; i < valores.length - 1; i++) {
                        cabecera.add(valores[i]);
                    }
                    primeraLinea = false;
                }
                else {
                    List<Double> datos = new ArrayList<>();
                    for (int i = 0; i < valores.length - 1; i++) {
                        datos.add(Double.parseDouble(valores[i]));
                    }
                    data.add(new RowWithLabel(datos, valores[valores.length - 1]));
                }
            }
        }

        return new TableWithLabels(cabecera, data);
    }
    private static String route(String fileName) throws IOException {
        try {
            URL recurso = CSV.class.getClassLoader().getResource(fileName);
            if (recurso == null) {
                throw new FileNotFoundException();
            }
            return new File(recurso.toURI()).getPath();
        } catch (URISyntaxException e) {
            throw new IOException();
        }
    }
}
