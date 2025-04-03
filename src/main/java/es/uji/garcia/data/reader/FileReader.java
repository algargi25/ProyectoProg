package es.uji.garcia.data.reader;

import es.uji.garcia.data.table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader <T extends Table> extends ReaderTemplate<T>{
    Scanner sc ;

    public FileReader(T table, String source) {
        super(table, source);
    }

    @Override
    protected void openSource(String source) {
        try {
            sc= new Scanner(new File(source));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error al abrir el archivo: " + source, e);
        }
    }

    @Override
    protected void processHeaders(String headers) {

    }

    @Override
    protected void processData(String data) {

    }

    @Override
    protected void closeSource() {
        if(sc != null){
            sc.close();
        }
    }

    @Override
    protected boolean hasMoreData() {
        return sc.hasNextLine();
    }

    @Override
    protected String getNextData() {
        return sc.nextLine();
    }
}
