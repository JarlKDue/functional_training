package org.example;

import org.example.dataobjects.AnnotatedStorageObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DisplayTemplate {

    public static void main(String[] args) throws IOException {
//        display(new AnnotatedBarn3());
        display(new AnnotatedBarn2());
        display(new AnnotatedAdult());
    }

    public static void display(AnnotatedStorageObject storageObject) {
        int x = 0;
        boolean running = true;
        Map<String, String> valuesMap = new HashMap<>();
        Class<?> clazz = storageObject.getClass();
        Map<Integer, String> displayElements = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(DisplayAndStore.class)) {
                displayElements.put(x++, (String) field.getName());
            }
        }
        displayElements.put(x++, "Submit");
        while(running){
            System.out.println(displayElements);
            System.out.println("Please Select Option To Modify");
            Scanner scanner = new Scanner(System.in);
            System.out.println(valuesMap);
            int choice = Integer.parseInt(scanner.next());
            if(displayElements.get(choice).equals("Submit")){
               running = false;
                storageObject.create_storage_object(valuesMap);
                System.out.println(storageObject.toString());
           } else {
                System.out.println("Please input value to store");
                valuesMap.put(displayElements.get(choice), scanner.next());
            }
        }
        }
    }