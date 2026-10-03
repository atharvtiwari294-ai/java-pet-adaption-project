package core;

public class NotificationThread extends Thread {

    private String message;

    public NotificationThread(String message) {
        this.message = message;
    }

   
    public void run() {
        // Simulating background notification sending work
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Notification] " + message + " (Sending..." + i + ")");
            try {
                Thread.sleep(1000); // delay for realism
            } catch (InterruptedException e) {
                System.out.println("Error in thread!");
            }
        }
        System.out.println("[Notification Sent Successfully!]");
    }
}
