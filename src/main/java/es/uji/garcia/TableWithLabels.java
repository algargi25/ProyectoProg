package es.uji.garcia;

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
    public TableWithLabels(List<String> headers, List<RowWithLabel> labeledRows) {
        super(headers,new ArrayList<>(labeledRows));
//        this.labelsToIndex = new HashMap<>();
        this.labeledRows = labeledRows;
    }

    public List<RowWithLabel> getLabeledRows() {
        return labeledRows;
    }
    public

    @Override
    public RowWithLabel getRowAt(int row){
        if (row >= 0 && row < labeledRows.size()) {
            return labeledRows.get(row);
        }
        throw new IndexOutOfBoundsException();
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
