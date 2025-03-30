package es.uji.garcia.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TableWithLabels extends Table{

    private List<RowWithLabel> rows;


    public TableWithLabels(List<String> headers, List<RowWithLabel> rows) {
        super(headers,new ArrayList<>(rows));
        this.rows = rows;
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
    public int getLabelAsInteger(String label){
        List<RowWithLabel> filas = rows;
        HashMap<String,Integer> result = new HashMap<>();
        int indice = 0;
        for(RowWithLabel fila : filas){
            String actLabel= fila.getLabel();
            if(!result.containsKey(actLabel)){
                result.put(actLabel, indice);
                indice++;
            }
        }
        return result.get(label);
    }

}
