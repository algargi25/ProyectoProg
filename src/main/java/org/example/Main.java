package org.example;

import java.io.IOException;
import java.net.URISyntaxException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Table table = CSV.readTable("miles_dollars.csv");
            System.out.println("Filas en la tabla: " + table.getRowCount());
                for (int i = 0; i < table.getRowCount(); i++) {
                    System.out.println(table.getRowAt(i).getData());
                }
            System.out.println("Encabezados: " + table.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    }
