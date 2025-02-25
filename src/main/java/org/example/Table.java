package org.example;

import java.util.List;

public class Table {
    private List<String> headers;
    private List<Row> filas;

    public Table(List<String> headers, List<Row> filas) {
        this.headers = headers;
        this.filas = filas;
    }



    public Row getRowAt(int rowNumber){
        return null;
    }
    public List<Double> getColumnAt(int columnNumber){
        return null;
    }
}
