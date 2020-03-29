package org.example;

import org.example.functions.List_Of_X_To_List_Of_Y;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

public class TestListOfXToListOfY {

    @Test
    public void testList(){
        Function<Child, Person> ChildToPerson = child  -> new Person(child.getName() + " " + child.getLastName(), Integer.parseInt(child.getAge()));

        List<Child> children = new ArrayList<>();
        children.add(new Child("Erik", "Mattson", "22"));
        children.add(new Child("David", "Mattson", "22"));
        children.add(new Child("Markus", "Mattson", "22"));
        children.add(new Child("Alex", "Mattson", "22"));
        children.add(new Child("Peter", "Mattson", "22"));
        children.add(new Child("Maria", "Mattson", "22"));
        children.add(new Child(null, "Mattson", "22"));
        children.add(new Child(null, "Mattson", "ABC"));
        Optional<List<Person>> people = List_Of_X_To_List_Of_Y.convert_x_to_y(Optional.of(children), ChildToPerson);
    }
}
