package be.kdg.java2.util;

import be.kdg.java2.model.Laptop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class LaptopFunctions {

    public static <T> List<T> filteredList(List<T> laptopList, Predicate<T> predicate){
        /*
          List<T> filteredLaptops = new ArrayList<>();
        for (T t : laptopList){
            if (predicate.test(t)){
                filteredLaptops.add(t);
            }
        } */

        return laptopList.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> Double average (List<T> laptopList, ToDoubleFunction<T> mapper) {
        /*
        double sum = 0;
        for (T t : laptopList) {
            sum += mapper.applyAsDouble(t);
        }

        return sum / laptopList.size();
*/
            return laptopList.stream()
                    .mapToDouble(mapper)
                    .average()
                    .orElse(0.0);
    }

    // TODO geeft alle laptops 15 terug "cond wordt niet toegepast"
    public static <T> long countIf(List<T> laptopList, Predicate<T> predicate){
//        long sum = 0;
//        for (T t : laptopList){
//            if (predicate.test(t)){
//                sum ++;
//            }
//
//        }
        return laptopList.stream().filter(predicate).count();
    }
}

