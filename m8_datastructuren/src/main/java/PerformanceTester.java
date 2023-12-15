//package be.kdg.java2;

import kollections.ArrayList;
//import be.kdg.java2.kollections.Kollections;
import  kollections.Kollections;
import  kollections.LinkedList;
import kollections.List;
import model.Laptop;
import model.LaptopFactory;

import java.util.Random;

public class PerformanceTester {

    //TODO: change this method for own use
    public static List<Laptop> randomList(int n) {
        List<Laptop> myList = new ArrayList<>();
        new Random().ints(n).forEach(i -> myList.add(LaptopFactory.newRandomLaptop()));
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        //add at beginning
        // TODO: use System.currentTimeMillis to compare performance of ArrayList en LinkedList
        //get at end
        //TODO: use System.currentTimeMillis to compare performance of ArrayList en LinkedList
        List<Laptop> arrayList = new ArrayList<>();
        List<Laptop> linkedList = new LinkedList<>();
        //add at beginning
        //arraylist
        long timeMillis = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> arrayList.add(0, LaptopFactory.newRandomLaptop()));
        long timeMillis1 = System.currentTimeMillis();
        long different1 = timeMillis1 - timeMillis;
        System.out.println("Adding "+ n + " to ArrayList: " + different1 + " ms");

        //linkedlist
        long timeMillis2 = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> linkedList.add(0, LaptopFactory.newRandomLaptop()));
        long timeMillis3 = System.currentTimeMillis();
        long different2 = timeMillis3 - timeMillis2;
        System.out.println("Adding "+ n + " to LinkedList: " + different2 + " ms");

        //get at end
        //arraylist
        long timeMillis4 = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> arrayList.get(n-1));
        long timeMillis5 = System.currentTimeMillis();
        long different3 = timeMillis5 - timeMillis4;
        System.out.println("Getting "+ n + " from ArrayList: " + different3 + " ms");

        //linkedlist
        long timeMillis6 = System.currentTimeMillis();
        new Random().ints(n).forEach(i -> linkedList.get(n-1));
        long timeMillis7 = System.currentTimeMillis();
        long different4 = timeMillis7 - timeMillis6;
        System.out.println("Getting "+ n + " from LinkedList: " + different4 + " ms");
    }


    public static void testSelectionSort() {
        //TODO: test selectionsort for (int n = 1000; n < 20000; n += 1000)
        for (int n = 1000; n < 20000; n += 1000){
            Kollections.selectionSort(randomList(n));
            System.out.println(n + ";" + Laptop.compareCounter);
        }
    }

    public static void testMergeSort() {
        //TODO: test mergesort for (int n = 1000; n < 200000; n += 1000)
        for (int n = 1000; n < 20000; n += 1000){
            Kollections.mergeSort(randomList(n));
            System.out.println(n + ";" + Laptop.compareCounter);
        }
    }
}
