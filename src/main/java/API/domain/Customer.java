package API.domain;

public class Customer {
    private int id;
    private String name, username, password;
    public Customer() {}
    public Customer(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}
