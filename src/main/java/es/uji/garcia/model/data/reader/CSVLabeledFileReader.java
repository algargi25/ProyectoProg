package es.uji.garcia.model.data.reader;

import es.uji.garcia.model.data.table.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends FileReader<TableWithLabels>{
    public CSVLabeledFileReader(String source) {
        super(source);
    }

    @Override
    protected void processHeaders(String headers) {
        String [] valores = headers.split(",");
        List<String> listaHeaders = new ArrayList<>();
        for (int i = 0; i < valores.length-1; i++) {
            listaHeaders.add(valores[i]);
        }
        table = new TableWithLabels(listaHeaders, new ArrayList<>());
    }


    protected void processData(String data){
        String[] values = data.split(",");
        List<Double> rowValues = new ArrayList<>();
        int longitud = values.length-1;
        for (int i = 0; i < longitud; i++) {
            rowValues.add(Double.parseDouble(values[i]));
        }
        table.addLabeledRow(rowValues, values[longitud]);
    }
}
