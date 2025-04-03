package es.uji.garcia.data.reader;

import es.uji.garcia.data.table.Table;

import java.util.Arrays;

public class CSVUnlabeledFileReader extends FileReader<Table> {
    public CSVUnlabeledFileReader(Table table, String source) {
        super(table, source);
    }
    protected void processData(String data, Table table){
        String[] values = data.split(",");
//        table.addRow();
    }
}
