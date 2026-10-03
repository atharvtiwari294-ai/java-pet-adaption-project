package core;

// Pet Model Class with Encapsulation + JDBC support
public class Pet {

    private int id;
    private String name;
    private String type;
    private String status;

    // Constructor
    public Pet(int id, String name, String type, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    // Getters (required for DAO + DB access)
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getStatus() {
        return status;
    }

    // Display Pet details
    public void showPetDetails() {
        System.out.println("Pet ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Status: " + status);
    }

    public String getSpecies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpecies'");
    }
}
