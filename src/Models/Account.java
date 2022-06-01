package src.Models;

import java.time.LocalDateTime;

public class Account {
    private long id;
    private String name;
    private double balance;
    private LocalDateTime created;
    private LocalDateTime last_update;
    private String note;

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

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
}
