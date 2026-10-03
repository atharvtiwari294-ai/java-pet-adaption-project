package database;

import java.util.ArrayList;
import core.Pet;

public class PetDAO implements DatabaseOperations<Pet> {
    // We use a static list so data persists while the app is running
    private static ArrayList<Pet> petList = new ArrayList<>();

    @Override
    public void add(Pet pet) {
        petList.add(pet);
        System.out.println("Pet added to memory: " + pet.getName());
    }

    public ArrayList<Pet> getAllPets() {
        return petList; // No SQL, no NullPointerException
    }

    @Override
    public void update(Pet pet) {
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getId() == pet.getId()) {
                petList.set(i, pet);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        petList.removeIf(p -> p.getId() == id);
    }

    @Override
    public Pet getById(int id) {
        return petList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}