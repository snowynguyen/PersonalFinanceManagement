package src.Models;

import src.Models.IndexableClass;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericTable<T extends IndexableClass> implements TableModel, Iterable<T>{
    private ArrayList<T> items;

    public GenericTable(GenericTable<T> table) {
        this.items = table.items;
    }

    public GenericTable(ArrayList<T> list) {
        this.items = list;
    }

    public GenericTable() {} 

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

    @Override
    public Object getValueAt(int row, int column) {
        return items.get(row).getValue(column);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setValueAt(Object arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getColumnName(int columnIndex) {
        T t;

        return null;
    }


}
