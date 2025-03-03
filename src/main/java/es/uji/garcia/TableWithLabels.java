package es.uji.garcia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{

    private List<RowWithLabel> labeledRows;


    public TableWithLabels(List<String> headers, List<RowWithLabel> labeledRows) {
        super(headers,new ArrayList<>(labeledRows));
        this.labeledRows = labeledRows;
    }
    public List<RowWithLabel> getLabeledRows() {
        return labeledRows;
    }
    @Override
    public RowWithLabel getRowAt(int row){
            return labeledRows.get(row);
    }
    public int getLabelAsInteger(String etiqueta){
        HashMap<String,Integer> mapaetiquetas = convierteMapa();
        return mapaetiquetas.get(etiqueta);
    }
    public HashMap<String,Integer> convierteMapa(){
        List<RowWithLabel> filas = labeledRows;
        HashMap<String,Integer> mapaetiquetas = new HashMap<>();
        int indice = 0;
        for(RowWithLabel fila : filas){
            String etiquetaactual= fila.getLabel();
            if(!mapaetiquetas.containsKey(etiquetaactual)){
                mapaetiquetas.put(etiquetaactual, indice);
                indice++;
            }
        }
        return mapaetiquetas;
    }
}
