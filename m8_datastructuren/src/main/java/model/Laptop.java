package model;

import java.time.LocalDate;
import java.util.Objects;


public class Laptop implements Comparable<Laptop>{
    private String name ;
    private String colour;
    private double price;
    private int ram;
    private int storage;
    private Category category;
    private LocalDate releaseDate;
    private String macAdres;

    public static long compareCounter = 0;

    public Laptop() throws IllegalArgumentException {
        this("Anoniem", "Ongekend", 0.0, 0, 0, Category.UNKNOWN  , LocalDate.now() , "onbekend");

    }

    public Laptop(String name, String colour, double price, int ram, int storage, Category category, LocalDate releaseDate , String macAdres) throws IllegalArgumentException {

        this.setName(name);
        this.setColour(colour);
        this.setPrice(price);
        this.setRam(ram);
        this.setStorage(storage);
        this.setCategory(category);
        this.setReleaseDate(releaseDate);
        this.setMacAdres(macAdres);

    }
    public void setName(String name) throws IllegalArgumentException {

        if ( name != null && !name.isEmpty()){
            this.name=name;

        }else {
            System.out.println("The name cannot be empty!!");
            throw  new IllegalArgumentException("Something went wrong 'with the name'!!");

        }
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setReleaseDate(LocalDate releaseDate) throws IllegalArgumentException{

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isBefore(releaseDate)){
            throw new IllegalArgumentException("Something went wrong 'with the releaseDate' ");


        }else {
            this.releaseDate = releaseDate;
            // System.out.println("releaseDate must be older than today");

        }
    }

    public void setMacAdres(String macAdres) {
        this.macAdres = macAdres;
    }

    public String getName(){
        return name;
    }

    public String getColour() {
        return colour;
    }

    public double getPrice() {
        return price;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getMacAdres(){return macAdres;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(macAdres, laptop.macAdres);
    }


    @Override
    public int hashCode() {
        return Objects.hash(macAdres);
    }


    @Override
    public int compareTo(Laptop o) {

        if (this.macAdres == null || o.macAdres == null) {
            if (this.macAdres == null && o.macAdres == null) {
                return 0; // Beide zijn null, dus ze zijn gelijk.
            } else if (this.macAdres == null) {
                return -1; // Dit object heeft een null macAdres, dus het is kleiner.
            } else {
                return 1; // Het andere object heeft een null macAdres, dus het is kleiner.
            }
        }
        compareCounter++;
        return this.macAdres.compareTo(o.macAdres);
    }

    @Override
    public String toString() {
        return String.format("Laptop: %s Kleur: %s Prijs: %.2fRAM: %d GB Storage: %d GB Categorie: %s Releasedatum: %s MacAdres %s" ,
                name, colour, price, ram, storage, category, releaseDate, macAdres);
    }

}
