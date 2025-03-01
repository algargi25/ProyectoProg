package org.example;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<String> headers;
    private List<Row> rows;

    public Table(List<String> headers) {
        this.headers = headers;
        this.rows = new ArrayList<>();
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public Row getRowAt(int index) {
        if (index >= 0 && index < rows.size()) {
            return rows.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public int getRowCount() {
        return rows.size();
    }

    public List<String> getHeaders() {
        return headers;
    }
    public List<Double> getColumnAt(int columnNumber){
        return null;
    }
}
