package org.example.datamapper;

public class Student implements Storeable {

    @Id
    public int id;

    @Store
    public String age;
    @Store
    public String name;

    public Student(String age, String name, int id){
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @SetValue("name")
    public void setName(String name) {
        this.name = name;
    }

    @SetValue("id")
    public void setId(int id) {
        this.id = id;
    }

    @SetValue("age")
    public void setAge(String age) {
        this.age = age;
    }

    public Student(){};

    @Override
    public String getType() {
        return "student";
    }

    @Override
    public Integer getId() {
        return id;
    }
}
