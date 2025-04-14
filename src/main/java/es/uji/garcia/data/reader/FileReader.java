package es.uji.garcia.data.reader;

import es.uji.garcia.data.table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class FileReader <T extends Table> extends ReaderTemplate<T>{
    private Scanner sc ;

    public FileReader(String source) {
        super(source);
    }

    @Override
    protected void openSource(String source) {
        try {
            sc= new Scanner(new File(route(source)));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        return sc!=null && sc.hasNextLine();
    }

    @Override
    protected String getNextData() {
        if (hasMoreData()){
            return sc.nextLine();
        }else {
            throw new IllegalStateException();
        }
    }

    private String route(String fileName) throws IOException {
        try {
            URL ruta = getClass().getClassLoader().getResource(fileName);
            if (ruta == null) {
                throw new FileNotFoundException("No se encuentra el archivo");
            }
            return new File(ruta.toURI()).getPath();
        } catch (URISyntaxException e) {
            throw new IOException();
        }
    }
}
