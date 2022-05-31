package src.Models;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class GenericTable<T> extends JTable{
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

}
