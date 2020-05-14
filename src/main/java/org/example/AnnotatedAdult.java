package org.example;

import org.example.dataobjects.AnnotatedStorageObject;

import java.util.Map;

public class AnnotatedAdult implements AnnotatedStorageObject {
    @DisplayAndStore
    String navn;
    @DisplayAndStore
    String alder;
    @DisplayAndStore
    String hojde;
    @DisplayAndStore
    String rolle;

    public AnnotatedAdult(){

    }

    @Override
    public void create_storage_object(Map<String, String> values) {
        this.navn = values.get("navn");
        this.alder = values.get("alder");
        this.hojde = values.get("hojde");
        this.rolle = values.get("rolle");
    }

    public void do_create_storage_object(Map<String, String> values){

    }

}
