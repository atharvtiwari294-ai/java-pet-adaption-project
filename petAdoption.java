import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import core.*;
import database.PetDAO;
import database.UserDAO;

public class petAdoption {
    private PetDAO petDAO;
    private UserDAO userDAO;
    private JTable petTable;
    private DefaultTableModel tableModel;
    private JTextArea logArea;

    public petAdoption() {
        // Initialize DAOs (Now working with ArrayLists)
        petDAO = new PetDAO();
        userDAO = new UserDAO();

        // Initialize our Users (Role-Based)
        User adopter = new Adopter("Rahul", "rahul@example.com");
        User shelter = new Shelter("Happy Paws", "shelter@gmail.com");
        User admin = new Admin("System Admin", "admin@pet.com");

        // Seed some initial data
        petDAO.add(new Pet(101, "Tommy", "Dog", "Available"));
        petDAO.add(new Pet(102, "Milo", "Cat", "Available"));

        createAndShowGUI(adopter, shelter, admin);
    }

    private void createAndShowGUI(User adopter, User shelter, User admin) {
        JFrame frame = new JFrame("Pet Adoption System (In-Memory)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLayout(new BorderLayout(10, 10));

        // --- 1. PET TABLE (Center) ---
        String[] columns = {"ID", "Pet Name", "Species", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        petTable = new JTable(tableModel);
        refreshTableData();

        // --- 2. LOG AREA (East) ---
        logArea = new JTextArea(10, 35);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setBackground(new Color(245, 245, 245));
        logArea.setBorder(BorderFactory.createTitledBorder("Activity Log"));
        logArea.append("System started in Offline Mode...\n");

        // --- 3. BUTTON PANEL (South) ---
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAdd = new JButton("Shelter: List Pet");
        JButton btnApply = new JButton("Adopter: Apply");
        JButton btnApprove = new JButton("Admin: Approve");
        JButton btnThread = new JButton("Send Notification");

        btnPanel.add(btnAdd);
        btnPanel.add(btnApply);
        btnPanel.add(btnApprove);
        btnPanel.add(btnThread);

        // --- BUTTON LOGIC ---

        // SHELTER ACTION
        btnAdd.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter Pet ID:");
            String name = JOptionPane.showInputDialog("Enter Pet Name:");
            String type = JOptionPane.showInputDialog("Enter Species:");
            if (idStr != null && name != null) {
                Pet newPet = new Pet(Integer.parseInt(idStr), name, type, "Available");
                petDAO.add(newPet);
                ((Shelter) shelter).listPet(newPet); // Call core logic
                refreshTableData();
                logArea.append("[SHELTER] " + shelter.getName() + " listed " + name + "\n");
            }
        });

        // ADOPTER ACTION
        btnApply.addActionListener(e -> {
            int row = petTable.getSelectedRow();
            if (row != -1) {
                Pet p = petDAO.getById((int) tableModel.getValueAt(row, 0));
                ((Adopter) adopter).applyForPet(p); // Call core logic
                logArea.append("[ADOPTER] " + adopter.getName() + " applied for " + p.getName() + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "Select a pet first!");
            }
        });

        // ADMIN ACTION
        btnApprove.addActionListener(e -> {
            int row = petTable.getSelectedRow();
            if (row != -1) {
                Pet p = petDAO.getById((int) tableModel.getValueAt(row, 0));
                ((Admin) admin).approvePet(p); // Call core logic
                logArea.append("[ADMIN] " + admin.getName() + " approved " + p.getName() + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "Select a pet to approve!");
            }
        });

        // THREAD ACTION
        btnThread.addActionListener(e -> {
            NotificationThread t = new NotificationThread("Adoption Process Update");
            t.start(); // Start background thread
            logArea.append("[THREAD] Notification process started in background...\n");
        });

        // ASSEMBLY
        frame.add(new JScrollPane(petTable), BorderLayout.CENTER);
        frame.add(new JScrollPane(logArea), BorderLayout.EAST);
        frame.add(btnPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refreshTableData() {
        tableModel.setRowCount(0);
        for (Pet p : petDAO.getAllPets()) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getType(), p.getStatus()});
        }
    }

    public static void main(String[] args) {
        // Use a modern UI look
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        new petAdoption();
    }
}