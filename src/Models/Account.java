package src.Models;

import java.time.LocalDateTime;

public class Account {
    private long id;
    private String name;
    private double balance;
    private LocalDateTime created;
    private LocalDateTime last_update;
    private String note;

    public Account () {
        this.created = LocalDateTime.now();
        this.last_update = LocalDateTime.now();
    }
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
        this.last_update = LocalDateTime.now();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.last_update = LocalDateTime.now();
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        this.last_update = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
        this.last_update = LocalDateTime.now();
    }

    public LocalDateTime getLast_update() {
        return this.last_update;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
        this.last_update = LocalDateTime.now();
    }

}