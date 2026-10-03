package core;

import java.util.ArrayList;

// Generic class to store any type of objects
public class DataStore<T> {

    private ArrayList<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public void showAll() {
        for (T item : items) {
            System.out.println(item.toString());
        }
    }

    public ArrayList<T> getItems() {
        return items;
    }
}
