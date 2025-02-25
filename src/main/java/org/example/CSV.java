package org.example;

import java.net.URISyntaxException;

public class CSV {
    String fichero = getClass().getClassLoader().getResource("iris.csv").toURI().getPath();



    public Table readTable(String valor){
        return null;
    }
    public TableWithLabels readTableWithLabels(String s){
        return null;
    }

}
