package core;

// User class with Encapsulation + Getters for JDBC
public class User {

    protected String name;
    protected String email;
    protected String role;

    // Constructor
    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters (required for DAO)
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }

    // Display Method
    public void showDetails() {
        System.out.println("\nUser Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}
