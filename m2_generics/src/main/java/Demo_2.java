import be.kdg.java2.Data.Data;
import be.kdg.java2.model.Category;
import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.PriorityQueue;

import java.time.LocalDate;
import java.util.Random;

public class Demo_2 {
    public static void main(String[] args) throws IllegalAccessException {
        PriorityQueue<String> myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for (int i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());


        System.out.println("\n--------'Laptops'-------\n");

        PriorityQueue<Laptop> laptopPriorityQueue = new PriorityQueue<>();

        Random random = new Random();
        //  int  randomNr = random.nextInt(1,5) +1 ; // value van de map


        for (Laptop laptop : Data.getData()) {
            var randomPriority = random.nextInt(5) + 1;
            var randomindex = random.nextInt(15);
            laptopPriorityQueue.enqueue(laptop, randomPriority);
        }

        // laat de inhoud van laptopPriorityQueue

        System.out.println(laptopPriorityQueue.toString());
        System.out.println("Aantal laptops: " + laptopPriorityQueue.getSize());

        Laptop searchLaptop = new Laptop("HP", "Zwart", 899.99, 16, 512, Category.GAMING, LocalDate.of(2022, 5, 15), "ABCD12345");

        int searchPosition = laptopPriorityQueue.search(searchLaptop);
        if (searchPosition != -1) {
            System.out.println("positon of Laptop:" + searchPosition);
        } else {
            System.out.println("Laptop not found");
        }

        Data data = new Data();
        var getSize = data.getSiceLaptop();

        // System.out.println("positon of Laptop:" + laptopPriorityQueue.search(searchLaptop));

        for (int i = 0; i < getSize; i++) {
            System.out.println("Dequeue: " + laptopPriorityQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + laptopPriorityQueue.getSize());


    }
}
