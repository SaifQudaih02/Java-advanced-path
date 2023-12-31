package be.kdg.java2.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class LaptopFunctions {

    public static <T> List<T> filteredList(List<T> laptopList, Predicate<T> predicate){

        return laptopList.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> Double average (List<T> laptopList, ToDoubleFunction<T> mapper) {
            return laptopList.stream()
                    .mapToDouble(mapper)
                    .average()
                    .orElse(0.0);
    }

    public static <T> long countIf(List<T> laptopList, Predicate<T> predicate){
        return laptopList.stream().filter(predicate).count();
    }
}

