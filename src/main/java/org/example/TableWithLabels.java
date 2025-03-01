package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{

    private Map<String, Integer> labelsToIndex; // Mapea etiquetas a números enteros
    private List<RowWithLabel> labeledRows; // Lista de filas con etiquetas

    /**
     * Constructor de TableWithLabels.
     * @param headers Lista con los nombres de las columnas.
     */
    public TableWithLabels(List<String> headers) {
        super(headers);
        this.labelsToIndex = new HashMap<>();
        this.labeledRows = new ArrayList<>();
    }

    @Override
    public RowWithLabel getRowAt(int row){
        if (row >= 0 && row < labeledRows.size()) {
            return labeledRows.get(row);
        }
        throw new IndexOutOfBoundsException();
    }
    public Integer getLabelAsInteger(String label){
        return labelsToIndex.getOrDefault(label, -1);
    }
}
