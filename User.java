public class User {
    String username; 
    String password;
    
    public boolean auth(String password) { 
        return password == this.password;
    } 

    
}
