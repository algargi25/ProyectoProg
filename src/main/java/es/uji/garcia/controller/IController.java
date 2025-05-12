package es.uji.garcia.controller;

import java.util.List;

public interface IController {
    public void init();
    List<String> readNames(String fileOfItemNames);
}
