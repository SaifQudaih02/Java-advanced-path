package be.kdg.java2.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * De basisklasse die de eigenschappen en gedragingen van een Laptop modelleert.
 *
 * @author Saif
 * @version 1.0
 * @see <a href="https://nl.wikipedia.org/wiki/Laptop">Laptop op Wikipedia</a>
 */
public class Laptop implements Comparable<Laptop>{
    private String name ;
    private String colour;
    private double price;
    private int ram;
    private int storage;
    private Category category;
    private LocalDate releaseDate;
    private String macAdres;

    public Laptop() throws IllegalAccessException {
        this("Anoniem", "Ongekend", 0.0, 0, 0, Category.UNKNOWN  , LocalDate.now() , "onbekend");

    }
    /**
     * Constructor voor het maken van een Laptop object met opgegeven eigenschappen.
     *
     * @param name       De naam van de laptop.
     * @param colour     De kleur van de laptop.
     * @param price      De prijs van de laptop.
     * @param ram        De hoeveelheid RAM-geheugen van de laptop.
     * @param storage    De opslagcapaciteit van de laptop.
     * @param category   De categorie van de laptop.
     * @param releaseDate De release datum van de laptop.
     * @param macAdres   Het MAC-adres van de laptop.
     * @throws IllegalAccessException Wordt gegenereerd als de naam leeg of null is.
     */
    public Laptop(String name, String colour, double price, int ram, int storage, Category category, LocalDate releaseDate , String macAdres) throws IllegalAccessException {

        this.setName(name);
        this.setColour(colour);
        this.setPrice(price);
        this.setRam(ram);
        this.setStorage(storage);
        this.setCategory(category);
        this.setReleaseDate(releaseDate);
        this.setMacAdres(macAdres);

    }

    /**
     * Stelt de naam van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param name De naam van de laptop.
     * @throws IllegalAccessException Wordt gegenereerd als de naam leeg of null is.
     */
    public void setName(String name) throws IllegalAccessException {

        if ( name != null && !name.isEmpty()){
            this.name=name;

        }else {
            System.out.println("The name cannot be empty!!");
            throw  new IllegalAccessException("Something went wrong 'with the name'!!");

        }
    }

    /**
     * Stelt de color van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param colour De naam van de laptop.
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Stelt de price van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param price De naam van de laptop.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Stelt de ram van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param ram De naam van de laptop.
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    /**
     * Stelt de storage van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param storage De naam van de laptop.
     */
    public void setStorage(int storage) {
        this.storage = storage;
    }

    /**
     * Stelt de category van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param category De naam van de laptop.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Stelt de relasedate van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param releaseDate De naam van de laptop.
     */
    public void setReleaseDate(LocalDate releaseDate) throws IllegalArgumentException{

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isBefore(releaseDate)){
            throw new IllegalArgumentException("Something went wrong 'with the releaseDate' ");


        }else {
            this.releaseDate = releaseDate;
            // System.out.println("releaseDate must be older than today");


        }
    }

    /**
     * Stelt de macAdres van de laptop in. De naam mag niet leeg of null zijn.
     *
     * @param macAdres De naam van de laptop.
     */
    public void setMacAdres(String macAdres) {
        this.macAdres = macAdres;
    }

    /**
     * Retourneert de naam van de laptop.
     *
     * @return De naam van de laptop.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourneert de kleur van de laptop.
     *
     * @return De kleur van de laptop.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Retourneert de prijs van de laptop.
     *
     * @return De prijs van de laptop.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retourneert de hoeveelheid RAM-geheugen van de laptop.
     *
     * @return De hoeveelheid RAM-geheugen van de laptop.
     */
    public int getRam() {
        return ram;
    }

    /**
     * Retourneert de opslagcapaciteit van de laptop.
     *
     * @return De opslagcapaciteit van de laptop.
     */
    public int getStorage() {
        return storage;
    }

    /**
     * Retourneert de categorie van de laptop.
     *
     * @return De categorie van de laptop.
     */
    public Category getCategory() {
        return category;
    }
    /**
     * Retourneert het MAC-adres van de laptop.
     *
     * @return Het MAC-adres van de laptop.
     */
    public String getMacAdres(){return macAdres;}
    /**
     * Bepaalt of twee laptops gelijk zijn op basis van hun MAC-adres.
     *
     * @param o Het object waarmee deze laptop wordt vergeleken.
     * @return true als de laptops hetzelfde MAC-adres hebben, anders false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(macAdres, laptop.macAdres);
    }

    /**
     * Geeft de hashcode van de laptop op basis van het MAC-adres.
     *
     * @return De hashcode van de laptop.
     */

    @Override
    public int hashCode() {
        return Objects.hash(macAdres);
    }

    /**
     * Vergelijkt deze laptop met een andere laptop op basis van hun MAC-adres.
     *
     * @param o De andere laptop waarmee deze laptop wordt vergeleken.
     * @return 0 als de MAC-adressen gelijk zijn, een negatieve waarde als deze laptop kleiner is,
     * een positieve waarde als deze laptop groter is dan de andere laptop.
     */
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
        return this.macAdres.compareTo(o.macAdres);
    }

    /**
     * Geeft een leesbare representatie van de laptop als een string.
     *
     * @return Een string die de naam, kleur, prijs, RAM-geheugen, opslag, categorie, release datum
     * en MAC-adres van de laptop bevat.
     */
    @Override
    public String toString() {
        return String.format("Laptop: %s Kleur: %s Prijs: %.2fRAM: %d GB Storage: %d GB Categorie: %s Releasedatum: %s MacAdres %s" ,
                name, colour, price, ram, storage, category, releaseDate, macAdres);
    }

}
