package src.Models;

public class TransactionCategory implements IndexableClass {
    private long id;
    private String name;

    private static final String [] __colnames = {"ID, Name"};

    public Object getValue(int index) {
        switch (index) {
            case 0: return id;
            case 1: return name;
        }
        return null;
    }

    public String getColumnName(int col) {
        if (0 <= col && col <= 1) {
            return __colnames[col];
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
