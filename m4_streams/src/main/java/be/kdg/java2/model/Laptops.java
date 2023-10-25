package be.kdg.java2.model;

import java.util.*;
import java.util.function.Function;

public class Laptops {

    private  TreeSet<Laptop> laptopSet;
    public Laptops() {
        laptopSet= new TreeSet<>();
    }

    public boolean addLaptop(Laptop laptop){
        return laptopSet.add(laptop);
    }

    public boolean remove(String macadres) {

        return laptopSet.removeIf(laptop -> {
            String laptopMacAdres = laptop.getMacAdres();
            return laptopMacAdres != null && laptopMacAdres.equals(macadres);
        });
    }


    public Laptop search(String macadres) {

        return laptopSet.stream()
                .filter(laptop -> {
                    String laptopMacAdres = laptop.getMacAdres();
                    return laptopMacAdres != null && laptopMacAdres.equals(macadres);
                })
                .findFirst()
                .orElse(null);
    }


    public List<Laptop> sortedBy(Function<Laptop, Comparable> function) {
        List<Laptop> myList = new ArrayList<Laptop>(laptopSet);
        myList.sort(Comparator.comparing(function));
        return myList;
    }

   public Set<Laptop> getAllLaptops(){
        return laptopSet;
   }

    public int getSize(){
        return laptopSet.size();
    }
}