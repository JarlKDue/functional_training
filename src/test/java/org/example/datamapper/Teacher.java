package org.example.datamapper;

public class Teacher implements Storeable {

    @Id
    public int teacherid;

    public Teacher(){

    }

    Teacher(String name, String age, String salary, int teacherid){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.teacherid = teacherid;
    }

    @Store
    public String age;
    @Store
    public String name;
    @Store
    public String salary;

    @Override
    public String getType() {
        return "teacher";
    }

    @Override
    public Integer getId() {
        return teacherid;
    }
}
