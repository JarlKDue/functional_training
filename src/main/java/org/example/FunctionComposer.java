package org.example;

import java.util.function.Function;

public class FunctionComposer {

    public static <A, B, C> Function<A, C> composeFunctions(Function<A, B> x, Function<B, C> y) {
        return x.andThen(y);
    }


}
