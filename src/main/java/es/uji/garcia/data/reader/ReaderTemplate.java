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
    protected abstract void openSource(String source);
    protected abstract void processHeaders(String headers);
    protected abstract void processData(String data);
    protected abstract void closeSource();
    protected abstract boolean hasMoreData();
    protected abstract String getNextData();
}
