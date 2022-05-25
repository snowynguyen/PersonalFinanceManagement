package src;

import java.time.LocalDateTime;
import java.util.Random;

public class Transaction {
    static Random __rng = new Random(73575334);
    static long __generateNewID() {
        // TODO: Check for duplicate. 
        return __rng.nextLong();
    }


    long id;
    LocalDateTime time;
    long account_id;
    String detail, note;
    double amount;
    long category_id;
    boolean is_future_reversible;
    LocalDateTime due_reversal;

    Transaction() {
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
