package be.kdg.java2.model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Laptops {

    private TreeSet<Laptop> laptopSet;

    private static final Logger LOGGER = Logger.getLogger(Laptops.class.getName());
    public Laptops() {
        laptopSet= new TreeSet<>();
    }

    public boolean setLaptop(Laptop laptop){
       // return laptopSet.add(laptop);
        boolean added = laptopSet.add(laptop);
        if (added) {
            LOGGER.log(Level.FINER, "Laptop added: " + laptop.getName());
        }
        return added;
    }

    public boolean remove(String macadres) {

        Iterator<Laptop> iterator = laptopSet.iterator();
        while (iterator.hasNext()) {
            Laptop laptop = iterator.next();
            String laptopMacAdres = laptop.getMacAdres();
            if (laptopMacAdres != null && laptopMacAdres.equals(macadres)) {
                iterator.remove();
                LOGGER.log(Level.FINER, "Laptop removed: " + laptop.getName());
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