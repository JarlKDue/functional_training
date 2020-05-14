package org.example.datamapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class testit {

    @Test
    public void build_database() throws IllegalAccessException {
        Student student = new Student();
        student.age = "31";
        student.name = "Mattias Erikson";

        Student student2 = new Student();
        student2.age = "31";
        student2.name = "Mattias Erikson";

        Student student3 = new Student();
        student3.age = "31";
        student3.name = "Mattias Erikson";

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);

        students.forEach(GenericMapperInterface::create);

        Teacher teacher = new Teacher();
        teacher.age = "49";
        teacher.name = "Alex Oreilly";
        teacher.salary = "39000";

        Teacher teacher2 = new Teacher();
        teacher2.age = "24";
        teacher2.name = "Peter Oreilly";
        teacher2.salary = "39000";

        Teacher teacher3 = new Teacher();
        teacher3.age = "43";
        teacher3.name = "Mark Oreilly";
        teacher3.salary = "39000";

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher2);
        teachers.add(teacher3);

        teachers.forEach(GenericMapperInterface::create);
    }

    @Test
    public void test_delete(){
        Student student = new Student();
        student.id = 67832;
        GenericMapperInterface.delete(student);
    }

    @Test
    public void get_all_students(){
       List<Storeable> studentList = GenericMapperInterface.get_all_storeable(new Student()).get();
       studentList.stream()
               .forEach(storeable -> System.out.println(storeable.getType()));
}

    @Test
    public void resetdatabase(){
        GenericMapperInterface.reset();
    }

    @Test
    public void build_db() throws IllegalAccessException {
        GenericMapperInterface.build_table(new Student());
        GenericMapperInterface.build_table(new Teacher());
        GenericMapperInterface.build_table(new Course());
    }

    @Test
    public void add_course() {
        Course course = new Course();
        course.courseDescription = "Learn English";
        course.courseEndDate = "22-20-2022";
        course.courseStartDate = "10-20-2021";
        course.courseName = "English 101";
        course.teacherid = 37287;

        Course course2 = new Course();
        course2.courseDescription = "Learn Greater English";
        course2.courseEndDate = "10-20-2024";
        course2.courseStartDate = "10-20-2021";
        course2.courseName = "English 201";
        course2.teacherid = 37287;
        GenericMapperInterface.create(course2);

        Course course3 = new Course();
        course3.courseDescription = "Learn Bestest English";
        course3.courseEndDate = "22-1-2023";
        course3.courseStartDate = "10-20-2021";
        course3.courseName = "English Superior";
        course3.teacherid = 37287;
        GenericMapperInterface.create(course3);
    }

}
