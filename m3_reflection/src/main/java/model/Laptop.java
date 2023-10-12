package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class Laptop extends Device {
    private String colour;
    private int ram;
    private int storage;
    private Category category;



    public Laptop(String colour, int ram, int storage, Category category) {
        this.colour = colour;
        this.ram = ram;
        this.storage = storage;
        this.category = category;
    }

    public Laptop(String name, double price, LocalDate releaseDate, String macAdres, String colour, int ram, int storage, Category category) {
        super(name, price, releaseDate, macAdres);
        this.colour = colour;
        this.ram = ram;
        this.storage = storage;
        this.category = category;
    }

    public Laptop(List<Laptop> data) {
    }




    public void setColour(String colour) {
        this.colour = colour;
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


    public String getColour() {
        return colour;
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


    @Override
    public String toString() {
        return String.format("Kleur: %s RAM: %d GB Storage: %d GB Categorie: %s" ,
                 colour, ram, storage, category);
    }

}
