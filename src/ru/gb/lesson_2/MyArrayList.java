package ru.gb.lesson_2;

import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> {
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity < 0 :" + capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        //проверить на переполнение.  при заполнении пересоздать внутренний массив
        // размером capacity *1.5 +1
        list[size] = item;
        size++;
    }

    public void add(int index, T item) {
        //проверить на переполнение.  при заполнении пересоздать внутренний массив
        // размером capacity *1.5 +1
        // проверить index

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    public final T delete(int index) {
        //проверить index

        T temp = list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
        return temp;
    }

    public boolean delete(T item) {
        int pos = index(item);
        if (pos < 0) {
            return false;
        }
        delete(pos);
        return true;
    }

    public T get(int index) {
        //проверить index
        return list[index];
    }

    public void set(int index, T item) {
        //проверить index
        list[index] = item;
    }

    public int indexOf(T item) {
        return index(item);
    }

    private int index(T item) {
        if (item == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    //O(n * n)
    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int iMin = i;
            for (int j = i; j < size; j++) {
                if (less(list[j], list[iMin])) {
                    iMin = j;
                }
            }
            swap(i, iMin);
        }
    }

    public void insertionSort() {
        T key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key = list[i];
            while (j > 0 && less(key, list[j - 1])) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = key;
        }
    }

    public void bubbleSort() {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                }
            }
        }
    }

    public void bubbleSortOpt() {
        boolean isSwap;
        for (int i = size - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                    isSwap = true;
                }
            }

            if (!isSwap) {
                break;
            }
        }
    }

    public void quickSort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        T opora = list[mid];

        int i = lo;
        int j = hi;
        while (i <= j) {
            while (less(list[i], opora)) {
                i++;
            }
            while (less(opora, list[j])) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (lo < j) {
            quickSort(lo, j);
        }
        if (hi > i) {
            quickSort(i, hi);
        }
    }

}