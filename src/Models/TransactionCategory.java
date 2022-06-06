package src.Models;

public class TransactionCategory implements IndexableClass {
    private long id;
    private String name;

    public Object getValue(int index) {
        switch (index) {
            case 0: return id;
            case 1: return name;
        }
        return null;
    }

    public static String getColumnName(int col) {
        switch (col) {
            case 0: return "id";
            case 1: return "name";
        }
        return null;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
