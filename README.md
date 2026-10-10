# 🐾 Online Pet Adoption System

A desktop **Pet Adoption Management System** built in **Java (Swing GUI)**. It simulates how a pet shelter, an adopter and an admin interact during the adoption process — listing pets, applying for adoption, approving requests and sending notifications.

The project is mainly built to demonstrate core **Object-Oriented Programming (OOP)** concepts in Java: inheritance, encapsulation, polymorphism, generics, interfaces, the DAO pattern and multithreading.

---

## ✨ Features

| Role | Action | What happens |
|------|--------|--------------|
| 🏠 **Shelter** | *List Pet* | Enter a Pet ID, name and species → the pet is added to the table with status `Available` |
| 🙋 **Adopter** | *Apply* | Select a pet in the table → an adoption application is logged |
| 🛡️ **Admin** | *Approve* | Select a pet in the table → the adoption is approved and logged |
| 🔔 **System** | *Send Notification* | Starts a background thread that "sends" a notification without freezing the GUI |

- **Pet table** – shows every pet (ID, Name, Species, Status) in the centre of the window
- **Activity log** – right-hand panel that records every action with a role tag (`[SHELTER]`, `[ADOPTER]`, `[ADMIN]`, `[THREAD]`)
- **Sample data** – starts with two pets: *Tommy* (Dog) and *Milo* (Cat)
- **In-memory storage** – data is kept in `ArrayList`s while the app runs (no database setup needed)

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 8 or newer)
- **GUI:** Java Swing (`JFrame`, `JTable`, `JTextArea`, `JOptionPane`)
- **Storage:** In-memory `ArrayList` via DAO classes
- **Optional:** MySQL + JDBC (`DatabaseConnection.java`, not used by the current GUI)

---

## 📁 Project Structure

```
online-pet-adoption/
├── petAdoption.java              # Main class – builds the Swing GUI and wires the buttons
├── core/                         # Business / model classes
│   ├── User.java                 # Base class (name, email, role)
│   ├── Adopter.java              # extends User → applyForPet()
│   ├── Shelter.java              # extends User → listPet()
│   ├── Admin.java                # extends User → approvePet()
│   ├── Pet.java                  # Pet model (id, name, type, status)
│   ├── DataStore.java            # Generic container class DataStore<T>
│   └── NotificationThread.java   # extends Thread → background notifications
└── database/                     # Data access layer
    ├── DatabaseOperations.java   # Generic interface: add / update / delete / getById
    ├── PetDAO.java               # Stores pets in a static ArrayList
    ├── UserDAO.java              # Stores users in an ArrayList
    └── DatabaseConnection.java   # MySQL JDBC connection helper (optional)
```

---

## 🧠 OOP Concepts Used

| Concept | Where it's used |
|---------|-----------------|
| **Encapsulation** | `Pet` and `User` keep fields `private`/`protected` and expose them through getters |
| **Inheritance** | `Adopter`, `Shelter` and `Admin` all extend `User` and call `super(...)` |
| **Polymorphism (overriding)** | Each subclass overrides `showDetails()`; objects are stored as `User` and cast to the specific role |
| **Abstraction / Interface** | `DatabaseOperations<T>` defines the CRUD contract that every DAO must follow |
| **Generics** | `DatabaseOperations<T>` and `DataStore<T>` work with any type |
| **DAO Pattern** | `PetDAO` / `UserDAO` separate data handling from GUI logic |
| **Multithreading** | `NotificationThread extends Thread` runs work in the background with `Thread.sleep()` |
| **Event handling** | Swing `ActionListener` lambdas on each button |

---

## 🚀 How to Run

### 1. Prerequisites
- Java JDK 8+ installed → check with:
  ```bash
  java -version
  javac -version
  ```

### 2. Clone the repository
```bash
git clone https://github.com/cnikhilkumar920-lang/online-pet-adoption.git
cd online-pet-adoption
```

### 3. Compile
```bash
javac -d out petAdoption.java core/*.java database/*.java
```

### 4. Run
```bash
java -cp out petAdoption
```

> 💡 You can also open the folder in **VS Code / IntelliJ / Eclipse** and run `petAdoption.java` directly.

---

## 🖥️ How to Use

1. Launch the app – a window opens with the pet table, activity log and four buttons.
2. Click **Shelter: List Pet** → enter ID, name and species to add a new pet.
3. Select a row in the table and click **Adopter: Apply** to apply for that pet.
4. Select a row and click **Admin: Approve** to approve the adoption.
5. Click **Send Notification** → watch the terminal; a background thread prints the notification steps over ~3 seconds while the GUI stays responsive.

---

## 🗄️ Optional: MySQL Setup

The project includes `database/DatabaseConnection.java` for connecting to MySQL using JDBC. It is **not used by the current version** (which runs fully in memory), but if you want to extend the project:

1. Create a database:
   ```sql
   CREATE DATABASE pet_adoption_db;
   ```
2. Add the MySQL Connector/J `.jar` to your classpath.
3. Update the URL, username and password in `DatabaseConnection.java`.

> ⚠️ **Don't commit real database passwords to GitHub.** Use environment variables or a local config file that is listed in `.gitignore`.

---

## 🔮 Future Improvements

- Update the pet's **status** (`Available` → `Pending` → `Adopted`) when an adopter applies and the admin approves
- Show notification messages inside the GUI instead of only in the terminal
- Input validation (e.g. non-numeric or duplicate Pet IDs)
- Connect the DAOs to MySQL for permanent storage
- Login screen with role-based access
- Search / filter pets by species or status
- Remove compiled `.class` files from the repo and add a `.gitignore`

---

## 👤 Author

**Nikhil Kumar** – [@cnikhilkumar920-lang](https://github.com/cnikhilkumar920-lang)

---

## 📄 License

This project is open-source and available for educational purposes.
