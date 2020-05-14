package org.example;

import org.junit.Test;

public class TestDisplayTemplate {

    @Test
    public void when_given_barn_display_stuff(){
        DisplayTemplate displayTemplate = new DisplayTemplate();
        displayTemplate.display(new AnnotatedBarn3());
    }

}
