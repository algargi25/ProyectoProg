package org.example;

public class TableWithLabels extends Table{

    @Override
    public RowWithLabel getRowAt(int row){
        return null;
    }
    public Integer getLabelAsInteger(String label){
        return 1;
    }
}
