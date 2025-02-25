package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class CSV {


    public CSV() {
    }

    public Table readTable(String valor) throws URISyntaxException {
        String fichero = Objects.requireNonNull(getClass().getClassLoader().getResource(valor)).toURI().getPath();
        Table tabla;
        Scanner sc = new Scanner(fichero);
        List<String> cab = new ArrayList<>();
        List<Row> elementos = new ArrayList<>();
        int lim = 0;
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] datos = linea.split(",");
                if(lim == 0){
                    cab = Arrays.asList(datos);
                    lim = 1;
                }else{
                    Row r =
                    elementos.add(Arrays.asList(datos));
                }
            }

        return tabla;
    }
    public TableWithLabels readTableWithLabels(String s){
        return null;
    }

}
