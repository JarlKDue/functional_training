package org.example;

import org.example.dataobjects.AnnotatedStorageObject;

import java.util.Map;

public class AnnotatedBarn3 implements AnnotatedStorageObject {
    @DisplayAndStore
    String navn;
    @DisplayAndStore
    String alder;
    @DisplayAndStore
    String hojde;
    @DisplayAndStore
    String mor;
    @DisplayAndStore
    String elev;
    @DisplayAndStore
    String far;

    public AnnotatedBarn3(){

    }

    @Override
    public void create_storage_object(Map<String, String> values) {
        this.navn = values.get("navn");
        this.alder = values.get("alder");
        this.hojde = values.get("hojde");
        this.mor = values.get("mor");
        this.elev = values.get("elev");
        this.far = values.get("far");
    }
}
