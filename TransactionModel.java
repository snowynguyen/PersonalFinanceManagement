class Account {
    User owner; 
    String name; 
    double balance;

    Account(User owner, String account_name) {
        this.owner = owner;
        this.name = account_name;
        this.balance = 0.0;
    }

    Account(User owner, String account_name, double balance) {
        this.owner = owner;
        this.name = account_name;
        this.balance = balance;
    }
}

class TransactingEntity {

}

public class TransactionModel {
    
}
