package org.example.functions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface List_Of_X_To_List_Of_Y {

    /**
     *
     * @param x_list List containing elements to convert
     * @param morphism Function describing how to conver the elements
     * @param <Y> Type to return
     * @param <X> Type to convert
     * @return an Optional List<Y>
     */
    static <Y, X> Optional<List<Y>> convert_x_to_y(Optional<List<X>> x_list, Function<X,Y> morphism){
        try {
            return x_list.map(xes -> xes.stream().map(morphism).collect(Collectors.toList()));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Optional.empty();
        }
    }
}
