package kollections.lists;

import kollections.Kollections;

public class ArrayList <E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void expand() {
        //TODO: implement this method
        // todo: als de size gelijk aan init_cap en je wilt new element toeveogen. gaat de size verdubbeld worden. Dus 10 overgeschreven worden door 20.
        Object [] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
//       if (size < INITIAL_CAPACITY) {
//           int newCapacity = elements.length * 2;
//           Object [] newElements = new Object[newCapacity]; // new array aanmaken met dubbel size
//           for (int i = 0; i < elements.length; i++) {
//               newElements[i] =elements[i]; // kopieer de elementen van de oude array naar de huidige.
//           }
//           elements=newElements; // de elementen aan de new array toewijzen
//       }
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        if (elements.length == size)
            expand();
        if (index == 0) {
            Object[] tempArr = new Object[elements.length + (size > elements.length ? 0 : 1)];
            System.arraycopy(elements, 0, tempArr, 1, elements.length);
            tempArr[0] = element;
            elements = tempArr;
        } else {
            elements[index] = element;
        }
        size++;

    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E oldValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
    }

    @Override
    public boolean remove(E element) {
        return false;

    }

    @Override
    public boolean contains(E element) {
        return false;
    }
}



