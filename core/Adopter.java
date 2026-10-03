package core;

import core.User;


public class Adopter extends User {

    public Adopter(String name, String email) {
        super(name, email, "ADOPTER");
    }

    public void applyForPet(Pet p) {
        System.out.println("\n" + name + " applied for pet: " + p.getName());
    }

    @Override
    public void showDetails() {
        System.out.println("\n(Adopter Account)");
        super.showDetails();
    }
}

