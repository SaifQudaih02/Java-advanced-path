package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.*;
@XmlRootElement(name = "laptops")
public class Laptops {

    private List<Laptop> laptopList ;
    public Laptops() {
        laptopList= new ArrayList<>();
    }

    public boolean addLaptop(Laptop laptop){
        return laptopList.add(laptop);
    }

    public boolean remove(String macadres) {
        Iterator<Laptop> iterator = laptopList.iterator();
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
        for (Laptop laptop : laptopList) {
            String laptopMacAdres = laptop.getMacAdres();
            if (laptopMacAdres != null && laptopMacAdres.equals(macadres)) {
                return laptop;
            }
        }
        return null;
    }

    public List<Laptop> sortedOnName() {
        List<Laptop> sortedList = new ArrayList<>(laptopList);
        sortedList.sort(new Comparator<Laptop>() {
            @Override
            public int compare(Laptop laptop1, Laptop laptop2) {
                return laptop1.getName().compareTo(laptop2.getName());
            }
        });
        return sortedList;
    }

    public List<Laptop> sortedOnPrice() {
        List<Laptop> sortedList = new ArrayList<>(laptopList);
        sortedList.sort(new Comparator<Laptop>() {
            @Override
            public int compare(Laptop laptop1, Laptop laptop2) {
                return Double.compare(laptop1.getPrice(), laptop2.getPrice());
            }
        });
        return sortedList;
    }
    public List<Laptop> sortedOnReleaseDate() {
        List<Laptop> sortedList = new ArrayList<>(laptopList);
        sortedList.sort(new Comparator<Laptop>() {
            @Override
            public int compare(Laptop laptop1, Laptop laptop2) {
                return laptop1.getReleaseDate().compareTo(laptop2.getReleaseDate());
            }
        });
        return sortedList;
    }



   public List<Laptop> getAllLaptops(){
        return laptopList;
   }

    public int getSize(){
        return laptopList.size();
    }

    public List<Laptop> getLaptopList() {
        return laptopList;
    }
    @XmlElement(name = "laptop")
    public void setLaptopList(List<Laptop> laptopList) {
        this.laptopList = laptopList;
    }


}