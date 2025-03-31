package es.uji.garcia.data.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TableWithLabels extends Table{

    private List<RowWithLabel> rows;
    private HashMap<String, Integer> etiquetas;

    public TableWithLabels(List<String> headers, List<RowWithLabel> rows) {
        super(headers,new ArrayList<>(rows));
        this.rows = rows;
        this.etiquetas = new HashMap<>();
        actEtiquetas();
    }
    public List<RowWithLabel> getLabeledRows() {
        return rows;
    }
    @Override
    public RowWithLabel getRowAt(int row){
            return rows.get(row);
    }
    public List<RowWithLabel> getRows(){
        return rows;
    }

    private void actEtiquetas(){
        int indice = 0;
        for (RowWithLabel fila : rows) {
            String etiqueta = fila.getLabel();
            if (!etiquetas.containsKey(etiqueta)) {
                etiquetas.putIfAbsent(etiqueta, indice++);
            }
        }
    }
    public int getLabelAsInteger(String label){
        return etiquetas.get(label);
    }


}
