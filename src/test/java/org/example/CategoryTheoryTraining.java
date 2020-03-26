package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.FunctionComposer.composeFunctions;

public class CategoryTheoryTraining {


    @Test
    public void get_new_function(){
       Function<String, Integer> StringToInteger = Integer::parseInt;
       Function<Integer, Double> IntegerMultiplied = Integer::doubleValue;
       Function<Double, String> DoubleToString = String::valueOf;

       Function<String, Double> newFunction = composeFunctions(StringToInteger, IntegerMultiplied);
       Double newDouble = newFunction.apply("1");
       Function<String, String> StringToDoubleString = composeFunctions(newFunction, DoubleToString);
       System.out.println(StringToDoubleString.apply("23"));
    }

    @Test
    public void test_child_conversions() {
        Function<Optional<List<Child>>, Optional<List<Person>>> ChildToPerson = optionalChildren -> {
           return optionalChildren.map(children -> children.stream().map(child -> new Person(child.getName() + " " + child.getLastName(), Integer.parseInt(child.getAge()))).collect(Collectors.toList()));
        };
        Function<Optional<List<Person>>, Optional<List<Student>>> PersonToStudent = optionalPersons -> {
            return optionalPersons.map(personList -> personList.stream().map(person -> new Student(person.fullName.split(" ")[0], person.fullName.split(" ")[1])).collect(Collectors.toList()));
        };
        Function<Optional<List<Student>>, Optional<List<String>>> GetStudentNames = optionalStudents -> {
            return optionalStudents.map(studentList -> studentList.stream().map(student -> student.first_name).collect(Collectors.toList()));
        };
        Function<Optional<List<Child>>, Optional<List<Student>>> ChildToStudent = composeFunctions(ChildToPerson, PersonToStudent);
        Function<Optional<List<Child>>, Optional<List<String>>> ChildToStudentToGetStudentNames = composeFunctions(ChildToStudent, GetStudentNames);

        List<Child> children = new ArrayList<>();
        List<Child> children2 = new ArrayList<>();
        List<Child> childrenwithnull = new ArrayList<>();
        children.add(new Child("Erik", "Mattson", "22"));
        children.add(new Child("David", "Mattson", "22"));
        children.add(new Child("Markus", "Mattson", "22"));
        children.add(new Child("Alex", "Mattson", "22"));
        children.add(new Child("Peter", "Mattson", "22"));
        children.add(new Child("Maria", "Mattson", "22"));
        childrenwithnull.add(new Child(null, null, null));
        Optional<List<String>> childrenNames = ChildToStudentToGetStudentNames.apply(Optional.of(children));
        Optional<List<String>> childrenNames2 = ChildToStudentToGetStudentNames.apply(Optional.of(children2));
        Optional<List<String>> childrenNamesWithNull = ChildToStudentToGetStudentNames.apply(Optional.of(childrenwithnull));

        childrenNames.ifPresent(System.out::println);
        childrenNames2.ifPresent(System.out::println);
        childrenNamesWithNull.ifPresent(System.out::println);
    }

}



class Child{
    String name;
    String lastName;
    String age;

    public Child(){

    }

    public Child(String name, String lastName, String age  ){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }
}


class Person{
    String fullName;
    int age;

    public Person(){

    }
    public Person(String fullName, int age){
    this.fullName = fullName;
    this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return fullName;
    }
}

class Student{
    String first_name;
    String last_name;

    public Student(){

    }

    public Student(String first_name, String last_name){
        setFirst_name(first_name);
        setLast_name(last_name);
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
