package org.example.datamapper;

public class Course implements Storeable {

    @Id
    int id;

    @Store
    String courseName;
    @Store
    String courseStartDate;
    @Store
    String courseEndDate;
    @Store
    String courseDescription;
    @Store
    @ForeignKey(value = "teacherid", table = "teacher")
    int teacherid;

    public Course(){

    }

    @Override
    public String getType() {
        return "courses";
    }

    @Override
    public Integer getId() {
        return id;
    }
}
