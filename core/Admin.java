package core;
import core.User;


// uding inheritance we will grant permision to admin to use data of user class
public class Admin extends User {

    // creating constructor of admin 
    public Admin(String name, String email) {
        super(name, email, "ADMIN");
    }

    // creating method for approvemethod
    public void approvePet(Pet p) {
        System.out.println("\nAdmin " + name + " approved the pet for adoption.");
    }

   // method for showdetails 
    public void showDetails() {
        System.out.println("\n(Admin Account)");
        super.showDetails();
    }
}