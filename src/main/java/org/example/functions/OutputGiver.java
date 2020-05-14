package org.example.functions;

import java.util.function.Function;

public interface OutputGiver<A> {
    public A giveOutput(String input);

    public default <B> OutputGiver<B> map(Function<A,B> f){
        return input -> f.apply(giveOutput(input));
    }
}

