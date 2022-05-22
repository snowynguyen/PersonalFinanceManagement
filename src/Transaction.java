package src;

import java.time.LocalDateTime;
import java.util.Random;

class TransactionCategory {
    long id;
    String name;
}

class Account {
    
}

class TransactingEntity {

}

public class Transaction {
    static Random __rng = new Random(73575334);
    static long __generateNewID() {
        // TODO: Check for duplicate. 
        return __rng.nextLong();
    }


    long id;
    LocalDateTime time;
    long origin_account_id, destination_account_id;
    String detail, note;
    double amount;
    long category_id;
    boolean isFuturePayable, isFutureReceivable;

    Transaction() {
        this.id = __generateNewID();
    }
}
