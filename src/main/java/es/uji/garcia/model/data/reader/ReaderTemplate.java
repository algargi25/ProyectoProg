package es.uji.garcia.model.data.reader;

import es.uji.garcia.model.data.table.Table;

public abstract class ReaderTemplate<T extends Table> {
    protected T table;
    private String source;

    public ReaderTemplate(String source) {
        this.source = source;
    }

    public final T readTableFromSource(){
        openSource(source);
        if(hasMoreData()){
            String header = getNextData();
            processHeaders(header);
        }
        while(hasMoreData()){
            String data = getNextData();
            processData(data);
        }
        closeSource();
        return table;
    }
    protected abstract void openSource(String source);
    protected abstract void processHeaders(String headers);
    protected abstract void processData(String data);
    protected abstract void closeSource();
    protected abstract boolean hasMoreData();
    protected abstract String getNextData();
}
