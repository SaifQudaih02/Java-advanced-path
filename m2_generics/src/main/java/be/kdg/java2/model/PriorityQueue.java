package be.kdg.java2.model;

import be.kdg.java2.model.FIFOQueue;

import java.util.*;
public class PriorityQueue<T> implements FIFOQueue<T> {
    private TreeMap<Integer, LinkedList<T>> priorityQueueMap;

    public PriorityQueue() {
        // TreeMap with keys in descending order
        this.priorityQueueMap = new TreeMap<>(Comparator.reverseOrder());
    }

    @Override
    public boolean enqueue(T element, int priority) {
        // Check if the element already exists in the queue.
        if (search(element) != -1) {
            return false;
        }

        // Check if there's an entry for this priority
        if (priorityQueueMap.containsKey(priority)) {
            priorityQueueMap.get(priority).addLast(element);
        } else {
            LinkedList<T> newPriorityList = new LinkedList<>();
            newPriorityList.add(element);
            priorityQueueMap.put(priority, newPriorityList);
        }

        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!!!!");
        }

        // Find the highest priority
        int highestPriority = priorityQueueMap.firstKey();
        LinkedList<T> highestPriorityList = priorityQueueMap.get(highestPriority);

        // Dequeue the oldest element from the highest priority list
        T element = highestPriorityList.removeFirst();

        // Remove the priority entry if it becomes empty
        if (highestPriorityList.isEmpty()) {
            priorityQueueMap.remove(highestPriority);
        }

        return element;
    }

    private boolean isEmpty() {
        return priorityQueueMap.isEmpty();
    }

    @Override
    public int search(T element) {
        for (Map.Entry<Integer, LinkedList<T>> entry : priorityQueueMap.entrySet()) {
           for (int i =0 ; i< entry.getValue().size(); i++) {

               if (entry.getValue().get(i).equals(element)){
                   return entry.getKey();
               }
//            LinkedList<T> list = entry.getValue();
//            int position = list.indexOf(element);
//            if (position != -1) {
//                int sum = priorityQueueMap.headMap(entry.getKey(), true).values().stream().mapToInt(pList -> pList.size()).sum();
//                return sum + position;
//            }
           }

        }
        return -1;
    }

    @Override
    public  int getSize() {
        return priorityQueueMap.size();
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, LinkedList<T>> entry : priorityQueueMap.entrySet()) {
            int priority = entry.getKey();
            LinkedList<T> elements = entry.getValue();
            for (T element : elements) {
                stringBuilder.append(priority).append(": ").append(element).append("\n");
            }
        }
        return stringBuilder.toString();
    }




}

