package User;

import Admin_package.Admin;
import Rooms.*;
import Visitors.Formal;
import Visitors.General;
import Visitors.Instructor;
import Visitors.Visitor;

import java.util.ArrayList;
import java.util.Scanner;
import FileH.FileHandler;

public class user {
    static Scanner input = new Scanner(System.in);
    public static int idStatic = 1;
    private static boolean loggedInUser = false;
    private String name;
    private String password;
    private String visitorType;
    private int id;

    // Constructor
    public user(String name, String password, String visitorType, int i) {
        this.name = name;
        this.password = password;
        this.id = idStatic;
        this.visitorType = visitorType;
        idStatic++;  // Increment the static id for the next User.user
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getVisitorType() {
        return visitorType;
    }

    public static int getIdStatic() {
        return idStatic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Password: " + password + ", Visitor type: " + visitorType ;
    }


    // Register method to add a User.user
    public static void validation(String name, String password,ArrayList<Visitor> visitors, FileHandler fileHandler) {
        if(name.length()<3||name.length()>25||password.length()<8){
            System.out.println("name must be between 3 and 25 characters and password must be more than 8 characters");
            register(visitors, fileHandler);
        }

    }
    public static Visitor register(ArrayList<Visitor> visitors, FileHandler fileHandler) {
        System.out.println("Enter the name of the account you want to register:");
        String name = input.next();
        System.out.println("Enter the password of the account you want to register:");
        String password = input.next();

        // First, check if the user already exists using the readVisitorsFromFile method
        ArrayList<Visitor> existingVisitors = fileHandler.readVisitorsFromFile("data/visitors.txt");

        // Check if the name already exists
        for (Visitor visitor : existingVisitors) {
            if (visitor.getName().equals(name)) {
                System.out.println("User with this name already exists. Please choose a different name.");
                register(visitors, fileHandler);// Return null to indicate that the registration failed
            }
        }

        user.validation(name, password, visitors, fileHandler);

        int choice = 0;
        String visitorType = "none";
        Visitor newVisitor = null;
        while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Choose\n1. Formal\n2. General\n3. Instructor");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    visitorType = "Formal";
                    break;
                case 2:
                    visitorType = "General";
                    break;
                case 3:
                    visitorType = "Instructor";
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    continue;
            }

            if (visitorType.equals("Formal")) {
                newVisitor = new Formal(name, password, visitorType);
            } else if (visitorType.equals("General")) {
                newVisitor = new General(name, password, visitorType);
            } else {
                newVisitor = new Instructor(name, password, visitorType);
            }
        }

        // Save the new visitor to the file
        fileHandler.saveVisitorToFile("data/visitors.txt", newVisitor);

        // Add the new visitor to the list and return the object
        visitors.add(newVisitor);
        System.out.println("Registration successful!");
        return newVisitor;
    }

    public static void login(ArrayList<Visitor> visitors, ArrayList<Room> teachingRooms, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Instructor> instructors, FileHandler fileHandler) {
        String nameLogin = "", passwordLogin = "";

        try {
            System.out.println("Login Page\n");
            System.out.println("Enter your Name:");
            nameLogin = input.next();
            System.out.println("Enter password:");
            passwordLogin = input.next();
        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
            input.nextLine(); // To clear the buffer
        }

        boolean loggedIn = false; // Reset loggedIn for each login attempt

        // Read the visitors from the file
        ArrayList<Visitor> existingVisitors = fileHandler.readVisitorsFromFile("data/visitors.txt");

        // Check if the entered name and password match any visitor in the file
        for (Visitor v : existingVisitors) {
            if (v.getName().equals(nameLogin) && v.getPassword().equals(passwordLogin)) {
                System.out.println("You logged in successfully.");
                Visitor winner = v.leaderBoard(visitors); // Get the winner from the leaderboard
                Visitor reward = v.rewardSys(); // Get the visitor who qualifies for a reward

                // Check if the logged-in user is the winner
                if (v.equals(winner)) {
                    System.out.println("Congratulations " + v.getName() + "! You are the winner!");
                    System.out.println("You won 7 free hours for the next month for " + v.getVisitorType() + " category.");
                }

                // Check if the logged-in user has earned a reward
                if (v.getTotalFreeHours() >= 1) {
                    System.out.println("Congratulations " + v.getName() + "! You have reached a milestone!");
                    System.out.println("You won 1 free hour");
                }

                // Call the visitor's options menu
                v.options(teachingRooms, meetingRooms, generalRooms, visitors, instructors, fileHandler);
                loggedIn = true;
                break;
            }
        }

        // If no match was found, handle wrong credentials
        if (!loggedIn) {
            int choice = 0;
            System.out.println("Wrong login or password. Please try again.\n");
            System.out.println("Do not have an account?");
            System.out.println("1. Try to login again.");
            System.out.println("2. Register a new Account.");

            // Handle user's choice to retry login or register a new account
            while (choice != 1 && choice != 2) {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        login(visitors, teachingRooms, meetingRooms, generalRooms, instructors, fileHandler);
                        return; // Avoid further execution
                    case 2:
                        Visitor newVisitor = register(visitors, fileHandler);
                        visitors.add(newVisitor); // Add the newly registered visitor to the list
                        return; // Avoid further execution
                }
            }
        }
    }


    // Menu for starting the application
    public static void startMenu(ArrayList<Visitor> visitors, ArrayList<Room>meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors , FileHandler fileHandler) {

        int choice = 0;
        do {
            try {
                System.out.println("\t\t\t\t\t\t\t\t\t***Hello to Galacticos Work space***");
                System.out.println("1. Create a new Account \n2. Login (Already have an account)");
                choice = input.nextInt();
            }
            catch (Exception e) {
                System.out.println("Invalid Input : "+e.getMessage());
                input.nextLine();
                choice = 4;
            }
            switch (choice) {
                case 1:
                    // Create a new User.user and add it to the list
                    Visitor newVisitor = register(visitors, fileHandler);
                    visitors.add(newVisitor);
                    startMenu(visitors,meetingRooms, generalRooms,teachingRooms, instructors, fileHandler);
                    break;
                case 2:
                    // Directly call the login function
                    login(visitors, teachingRooms, meetingRooms, generalRooms, instructors, fileHandler);
                    break;
                case 3:
                    Admin.adminLogin(visitors,meetingRooms, generalRooms,teachingRooms, instructors, fileHandler);
                    break;
                case 4:
                    startMenu(visitors,meetingRooms, generalRooms, teachingRooms, instructors, fileHandler);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }while (choice > 3);
    }
}