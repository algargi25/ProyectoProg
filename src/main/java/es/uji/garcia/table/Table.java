package es.uji.garcia.table;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<String> headers;
    protected List<Row> rows;

    public Table(List<String> headers, List<Row> rows) {
        this.headers = headers;
        this.rows = rows;
    }


    public void setHeaders(List<String> headers) {
        this.headers = headers;
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
        List<Double> resultado = new ArrayList<>();
        for (Row actual : rows){
            resultado.add(actual.getDataAt(columnNumber));
        }
        return resultado;
    }
}
