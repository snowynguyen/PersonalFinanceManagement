package src.Models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GenericTable<T> extends JTable implements Iterable<T>{
    private ArrayList<T> items;

    public ArrayList<T> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    public void add(T item) {
        this.items.add(item);
    }

    public Iterator<T> iterator() {
        return items.iterator();
    }

    public void merge(GenericTable<T> rhs) {
        for (T item : rhs) {
            this.add(item);
        }
    }

}
