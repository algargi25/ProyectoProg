package es.uji.garcia.data.reader;

import es.uji.garcia.data.table.Table;

public abstract class ReaderTemplate<T extends Table> {
    private T table;
    private String source;
    private String headers;
    private String data;

    public ReaderTemplate(T table, String source) {
        this.table = table;
        this.source = source;
    }

    public final T readTableFromSource(){
        openSource(source);
        processHeaders(headers);
        processData(data);
        closeSource();
        return null;
    }
    protected void openSource(String source){

    }
    protected void processHeaders(String headers){

    }
    protected void processData(String data){

    }
    protected void closeSource(){

    }
    protected boolean hasMoreData(){
        return true;
    }
    protected String getNextData(){
        return "";
    }
}
