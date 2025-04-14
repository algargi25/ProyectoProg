package es.uji.garcia.data.table;

import java.util.List;

public class RowWithLabel extends Row{

    public String label;

    public RowWithLabel(List<Double> data, String label) {
        super(data);
        this.label=label;
    }


    public String getLabel() {
        return label;
    }
}
