package model;

import reflection.CanRun;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Objects;

public class Device implements Comparable<Device>{
    private String name ;
    private double price;
    private LocalDate releaseDate;
    private String macAdres;

    public Device(){}

    public Device(String name, double price, LocalDate releaseDate, String macAdres) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
        this.macAdres = macAdres;
    }

    @CanRun("HP")
    public void setName(String name) throws IllegalAccessException {

        if ( name != null && !name.isEmpty()){
            this.name=name;

        }else {
            System.out.println("The name cannot be empty!!");
            throw  new IllegalAccessException("Something went wrong 'with the name'!!");

        }
    }

    public String getName(){
        return name;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    @CanRun
    public void setMacAdres(String macAdres) {
        this.macAdres = macAdres;
    }

    public String getMacAdres(){return macAdres;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return macAdres.equals(device.macAdres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(macAdres);
    }


    @Override
    public int compareTo(Device o) {
        if (this.macAdres == null || o.macAdres == null) {
            if (this.macAdres == null && o.macAdres == null) {
                return 0; // Beide zijn null, dus ze zijn gelijk.
            } else if (this.macAdres == null) {
                return -1; // Dit object heeft een null macAdres, dus het is kleiner.
            } else {
                return 1; // Het andere object heeft een null macAdres, dus het is kleiner.
            }
        }
        return this.macAdres.compareTo(o.macAdres);
    }

    @Override
    public String toString() {
        return String.format("Laptop: %s  Prijs: %.2f :Releasedatum: %s MacAdres %s" ,
                name, price, releaseDate, macAdres);
    }
}
