package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

public class Laptops implements Serializable {

    private TreeSet<Laptop> laptopSet;
    @Serial
    private static final long serialVersionUID = 1L;
    public Laptops() {
        laptopSet= new TreeSet<>();
    }

    public boolean setLaptop(Laptop laptop){
        return laptopSet.add(laptop);
    }

    public boolean remove(String macadres) {
        Iterator<Laptop> iterator = laptopSet.iterator();
        while (iterator.hasNext()) {
            Laptop laptop = iterator.next();
            String laptopMacAdres = laptop.getMacAdres();
            if (laptopMacAdres != null && laptopMacAdres.equals(macadres)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    public Laptop search(String macadres) {
        for (Laptop laptop : laptopSet) {
            String laptopMacAdres = laptop.getMacAdres();
            if (laptopMacAdres != null && laptopMacAdres.equals(macadres)) {
                return laptop;
            }
        }
        return null;
    }

    public List<Laptop> sortedOnName() {
        List<Laptop> sortedList = new ArrayList<>(laptopSet);
        sortedList.sort(new Comparator<Laptop>() {
            @Override
            public int compare(Laptop laptop1, Laptop laptop2) {
                return laptop1.getName().compareTo(laptop2.getName());
            }
        });
        return sortedList;
    }

    public List<Laptop> sortedOnPrice() {
        List<Laptop> sortedList = new ArrayList<>(laptopSet);
        sortedList.sort(new Comparator<Laptop>() {
            @Override
            public int compare(Laptop laptop1, Laptop laptop2) {
                return Double.compare(laptop1.getPrice(), laptop2.getPrice());
            }
        });
        return sortedList;
    }
    public List<Laptop> sortedOnReleaseDate() {
        List<Laptop> sortedList = new ArrayList<>(laptopSet);
        sortedList.sort(new Comparator<Laptop>() {
            @Override
            public int compare(Laptop laptop1, Laptop laptop2) {
                return laptop1.getReleaseDate().compareTo(laptop2.getReleaseDate());
            }
        });
        return sortedList;
    }



   public Set<Laptop> getAllLaptops(){
        return laptopSet;
   }

    public int getSize(){
        return laptopSet.size();
    }
}