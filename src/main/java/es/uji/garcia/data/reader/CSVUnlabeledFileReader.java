package es.uji.garcia.data.reader;

import es.uji.garcia.data.table.Row;
import es.uji.garcia.data.table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader<Table> {
    public CSVUnlabeledFileReader(String source) {
        super(source);
    }

    @Override
    protected void processHeaders(String headers) {
        List<String> listaHeaders = new ArrayList<>();
        for (String header: headers.split(",")){
            listaHeaders.add(header);
        }
        table = new Table(listaHeaders, new ArrayList<>());
    }


    protected void processData(String data){
        String[] values = data.split(",");
        List<Double> rowValues = new ArrayList<>();
        for (String value : values) {
            rowValues.add(Double.parseDouble(value));
        }
        Row r = new Row(rowValues);
        table.addRow(r);
    }
}
