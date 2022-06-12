package src.Models;

import java.time.LocalDateTime;

public class Account implements IndexableClass{
    private long id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime last_update;
    private String note;

    private static final String[] __colnames = {"ID", "Account Name", "Date Created", "Last Update", "Note"};


    public Object getValue(int index) {
        switch (index) {
            case 0: return id;
            case 1: return name;
            case 2: return created;
            case 3: return last_update;
            case 4: return note;
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

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLast_update() {
        return this.last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account () {
        this.created = LocalDateTime.now();
        this.last_update = LocalDateTime.now();
    }

    @Override
    public String getColumnName(int index) {
        if (index >= 0 && index < 5) {
            return __colnames[index];
        }
        return null;
    }
}
