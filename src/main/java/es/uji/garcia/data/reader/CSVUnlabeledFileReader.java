package es.uji.garcia.data.reader;

import es.uji.garcia.data.table.Row;
import es.uji.garcia.data.table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader<Table> {
    public CSVUnlabeledFileReader(Table table, String source) {
        super(table, source);
    }
    protected void processData(String data, Table table){
        String[] values = data.split(",");
        List<Double> rowValues = new ArrayList<>();
        for (String value : values) {
            rowValues.add(Double.parseDouble(value));
        }
        Row r = new Row(rowValues);
        table.addRow(r);
    }
}
