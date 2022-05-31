package src.Models;

import java.time.LocalDateTime;
import java.util.Random;

public class Transaction {
    static Random __rng = new Random(73575334);
    static long __generateNewID() {
        // TODO: Check for duplicate. 
        return __rng.nextLong();
    }

    private long id;
    private LocalDateTime time;
    private long account_id;
    private String detail;
    private String note;
    private double amount;
    private long category_id;
    private boolean is_future_reversible;
    private LocalDateTime due_reversal;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getAccount_id() {
        return this.account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public boolean getIs_future_reversible() {
        return this.is_future_reversible;
    }

    public void setIs_future_reversible(boolean is_future_reversible) {
        this.is_future_reversible = is_future_reversible;
    }

    public LocalDateTime getDue_reversal() {
        return this.due_reversal;
    }

    public void setDue_reversal(LocalDateTime due_reversal) {
        this.due_reversal = due_reversal;
    }

    public Transaction() {
        this.id = __generateNewID();
        this.time = LocalDateTime.MIN;
        this.account_id = 0;
        this.detail = "";
        this.note = "";
        this.amount = 0.0;
        this.category_id = 0;
        this.is_future_reversible = false;
        this.due_reversal = LocalDateTime.MAX;
    }
}
