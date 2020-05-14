package org.example.datamapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public interface GenericMapperInterface {

    static void build_table(Storeable storeable){
        try {
            build_table_based_on_storeable(storeable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void update(Storeable itemToStore) throws IllegalAccessException {
        Map<String, Object> jsonElementsMap = get_json_elements(itemToStore, new HashMap<>());
        // Create Update Query Based on Object and Reflection
        String prefix = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" update ").append(itemToStore.getType()).append(" set ");
        stringBuilder.append(" (");
        for (String key : jsonElementsMap.keySet()) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(key);
        }
        stringBuilder.append(")").append(" values (");
        prefix = "";
        for (String key : jsonElementsMap.keySet()) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append("?");
        }
        stringBuilder.append(")");
        Map<Integer, String> values = new HashMap<>();
        int x = 1;
        for(String key : jsonElementsMap.keySet()){
            values.put(x, (String) jsonElementsMap.get(key));
            x++;
        }
    }

    static void create(Storeable itemToStore) {
        try {
            Map<String, Object> jsonElementsMap = get_json_elements(itemToStore, new HashMap<>());

            Map<Integer, Object> values = new HashMap<>();
            int x = 1;
            for (String key : jsonElementsMap.keySet()) {
                values.put(x, jsonElementsMap.get(key));
                x++;
            }
            execute_statement(get_prepared_statement("create", jsonElementsMap, itemToStore.getType()), values);
        } catch (Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }



    static void delete(Storeable storeable){
        Map<Integer, Integer> values = new HashMap<>();
        values.put(1, storeable.getId());
        execute_statement(" delete from " + storeable.getType() + " where id = ?", values);
    }

    static void reset(){
        execute_statement("delete from teacher");
        execute_statement("delete from student");
    }

    static void read(Object object){

    }

    static  Optional<List<Storeable>> get_all_storeable(Storeable type){
        List<Storeable> fetched_storeables = new ArrayList<>();
        String query = "select * from " + type.getType();
        getConnection().ifPresent(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                Class<?> clazz = type.getClass();
                Constructor<?>[] constructors = clazz.getConstructors();
                Constructor constructor = constructors[1];
                Class clazz2 = constructor.getDeclaringClass();
                Storeable student = (Storeable) clazz.cast(type);
                Method[] methods = clazz.getMethods();
                Map<String, Method> methodMap = new HashMap<>();
                for(Method method : methods){
                    if(method.isAnnotationPresent(SetValue.class)){
                        methodMap.put(method.getAnnotation(SetValue.class).value(), method);
                    }
                }
                Map<String, Object> returnMap = get_json_elements(type, new HashMap<>());
                while(resultSet.next()){
                    for(String key : methodMap.keySet()){
                    }
                    fetched_storeables.add(student);
                }
                for(Storeable storeable : fetched_storeables){

                }
                connection.close();
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return Optional.of(fetched_storeables);
    }

    private static String get_prepared_statement(String type, Map<String, Object> elementsMap, String table){
        switch(type){
            case "update" :{
                return "MEEH";
            }
            case "delete" : {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" delete from " + table + " where id =" + elementsMap.get(1));
                return "Delete";
            }
            case "create" : {
                String prefix = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" insert into ").append(table);
                stringBuilder.append(" (");
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append(key);
                }
                stringBuilder.append(")").append(" values (");
                prefix = "";
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append("?");
                }
                stringBuilder.append(")");
                return stringBuilder.toString();
            }
            case "get" : return "Delete";
        }
        return null;
    }

    private static void execute_statement(String query, Map<Integer, ?> values){
        getConnection().ifPresent(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                for(Integer key : values.keySet()) {
                    preparedStatement.setObject(key, values.get(key));
                }
                preparedStatement.execute();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private static void execute_statement(String query){
        getConnection().ifPresent(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private static Optional<Connection> getConnection(){
        try {
            String myUrl = "jdbc:mysql://localhost:3306/school";
            Connection conn = DriverManager.getConnection(myUrl, "root", "admin");
            return Optional.ofNullable(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> get_json_elements(Storeable itemToStore, Map<String, Object> returnMap) throws IllegalAccessException {
        Class<?> clazz = itemToStore.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Store.class)) {
                if(field.getType().getTypeName().equals("java.lang.String")) {
                    returnMap.put((field.getName()), (String) field.get(itemToStore));
                } else {
                    returnMap.put(field.getName(), field.get(itemToStore));
                }
            }
            if (field.isAnnotationPresent(Id.class)) {
                Random random = new Random();
                returnMap.put(field.getName(), Integer.toString(random.nextInt(100000)));
            }
        }
        return returnMap;
    }

    private static void build_table_based_on_storeable(Storeable storeable) throws IllegalAccessException {
        Class<?> clazz = storeable.getClass();
        StringBuilder query = new StringBuilder().append("CREATE TABLE IF NOT EXISTS " + storeable.getType() + " (");
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Store.class)) {
                switch(field.getType().getTypeName()){
                   case "java.lang.String" : {
                       query.append(field.getName()).append(" VARCHAR(255),");
                       break;
                   }
                    case "int" : {
                        query.append(field.getName()).append(" INT,");
                        break;
                    }
               }
            }
            if (field.isAnnotationPresent(Id.class)) {
                    query.append(field.getName()).append(" INT PRIMARY KEY,");
            }
            if(field.isAnnotationPresent(ForeignKey.class)){
                query.append(" FOREIGN KEY (" + field.getAnnotation(ForeignKey.class).value() + ") REFERENCES " + field.getAnnotation(ForeignKey.class).table() + " (" + field.getAnnotation(ForeignKey.class).value() + "))");
            }
        }
        query.deleteCharAt(query.length()-1);
        query.append(")");
        execute_statement(query.toString());
    }

}
