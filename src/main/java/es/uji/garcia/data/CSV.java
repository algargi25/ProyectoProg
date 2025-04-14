package es.uji.garcia.data;

import es.uji.garcia.data.table.Row;
import es.uji.garcia.data.table.RowWithLabel;
import es.uji.garcia.data.table.Table;
import es.uji.garcia.data.table.TableWithLabels;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class CSV {


    public CSV() {
    }

    public Table readTable(String valor) throws URISyntaxException, IOException {
        String fichero = route(valor);
        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String line;
        String[] headers = br.readLine().split(",");
        List<Row> rw = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            List<Double> rowValues = new ArrayList<>();
            for (String value : values) {
                rowValues.add(Double.parseDouble(value));
            }
            Row fila = new Row(rowValues);
            rw.add(fila);
        }
        return new Table(Arrays.asList(headers), rw);

    }


    public TableWithLabels readTableWithLabels(String fileName) throws IOException {

        List<String> cabecera = new ArrayList<>();
        List<RowWithLabel> data = new ArrayList<>();
        String fichero = route(fileName);
        BufferedReader br = new BufferedReader(new FileReader(fichero));
        String line;
        boolean primeraLinea = true;
        while ((line = br.readLine()) != null) {
            String[] valores = line.split(",");
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
        return new TableWithLabels(cabecera, data);
    }
    private String route(String fileName) throws IOException {
        try {
            URL ruta = CSV.class.getClassLoader().getResource(fileName);
            if (ruta == null) {
                throw new FileNotFoundException("No se encuentra el archivo");
            }
            return new File(ruta.toURI()).getPath();
        } catch (URISyntaxException e) {
            throw new IOException();
        }
    }
}