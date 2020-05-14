package org.example;

import org.example.dataobjects.AnnotatedStorageObject;

import java.util.Map;

public class AnnotatedBarn2 implements AnnotatedStorageObject {
    @DisplayAndStore
    String navn;
    @DisplayAndStore
    String alder;
    @DisplayAndStore
    String enrolled;
    @DisplayAndStore
    String weight;

    String normalize;
    boolean ugly;

    public AnnotatedBarn2(){

    }

    @Override
    public void create_storage_object(Map<String, String> values) {
        this.navn = values.get("navn");
        this.alder = values.get("alder");
        this.enrolled = values.get("enrolled");
        this.weight = values.get("weight");
    }
}
