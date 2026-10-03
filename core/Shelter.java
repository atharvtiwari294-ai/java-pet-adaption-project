package core;
import core.User;

public class Shelter extends User {

    public Shelter(String name, String email) {
        super(name, email, "SHELTER");
    }

    // Shelter specific functionality
    public void listPet(Pet pet) {
        System.out.println("\nShelter " + name + " listed a new pet for adoption!");
        pet.showPetDetails();
    }

    @Override
    public void showDetails() {
        System.out.println("\n(Shelter Account)");
        super.showDetails();
    }
}
