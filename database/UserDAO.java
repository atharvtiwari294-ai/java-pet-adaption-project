package database;

import java.util.ArrayList;
import core.User;

public class UserDAO implements DatabaseOperations<User> {

    private ArrayList<User> userList = new ArrayList<>();

    @Override
    public void add(User user) {
        userList.add(user);
        System.out.println("User added: " + user);
    }

    @Override
    public void update(User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(updatedUser.getEmail())) {
                userList.set(i, updatedUser);
                System.out.println("User Updated Successfully!");
                return;
            }
        }
        System.out.println("User Not Found!");
    }

    @Override
    public void delete(int id) {
        // No user ID defined in User class (only name/email)
        System.out.println("Delete by ID not supported here!");
    }

    @Override
    public User getById(int id) {
        return null; // optional: implement based on role/email instead of ID
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }
}

