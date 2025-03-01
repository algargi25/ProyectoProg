package org.example;

import java.util.List;

public class Row {
    private List<Double> data;

    public Row(List<Double> data) {
        this.data = data;
    }

    public Double getDataAt(int index) {
        return data.get(index);
    }

    public List<Double> getData() {
        return data;
    }
}
