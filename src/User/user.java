package User;

import Admin_package.Admin;
import Rooms.*;
import Visitors.Instructor;
import Visitors.Visitor;

import java.util.ArrayList;
import java.util.Scanner;

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
    public user(ArrayList<user> users, ArrayList<Room>meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        startMenu(users,meetingRooms, generalRooms,teachingRooms, instructors);
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
    public static user register(ArrayList<user> users) {
        System.out.println("Enter the name of the account you want to register:");
        String name = input.next();
        System.out.println("Enter the password of the account you want to register:");
        String password = input.next();
        int choice=0;
        String visitorType="none";
        while(choice!=1&&choice!=2&&choice!=3)
        {
            System.out.println("Choose \n1.Formal\n2.General\n3.Instructor");
            choice= input.nextInt();
            switch(choice)
            {
                case 1:
                    visitorType="Formal";
                    break;
                case 2:
                    visitorType="General";
                    break;
                case 3:
                    visitorType="Instructor";
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        // Create a new User object and return it

        return new user(name, password,visitorType,idStatic);


    }

    // Login method to validate User.user credentials
//    static boolean loggedIn = false;
//    public boolean isLoggedIn() {return loggedIn;}
    public static void login(ArrayList<user> users) {
        System.out.println("Login Page\n");
        String nameLogin, passwordLogin;
        System.out.println("Enter your Name:");
        nameLogin = input.next();
        System.out.println("Enter password:");
        passwordLogin = input.next();

        boolean loggedIn = false; // Reset loggedIn for each login attempt

        for (user u : users) {
            if (u.getName().equals(nameLogin) && u.getPassword().equals(passwordLogin)) {
                System.out.println("You logged in successfully.");
                loggedIn = true;
                break;
            }
        }

        if (!loggedIn) {
            int choice = 0;
            System.out.println("Wrong login or password. Please try again.\n");
            System.out.println("Do not have an account?");
            System.out.println("1. Try to login again.");
            System.out.println("2. Register a new Account.");
            while (choice != 1 && choice != 2) {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        login(users);
                        return; // Avoid further execution
                    case 2:
                        user newUser = register(users);
                        users.add(newUser);
                        return; // Avoid further execution
                }
            }
        }
    }


    // Menu for starting the application
    public static void startMenu(ArrayList<user> users, ArrayList<Room>meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {

        int choice = 0;
        do {
            System.out.println("\t\t\t\t\t\t\t\t\t***Hello to Galacticos Work space***");
            System.out.println("1. Create a new Account \n2. Login (Already have an account)");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Create a new User.user and add it to the list
                    user newUser = register(users);
                    users.add(newUser);
                    startMenu(users,meetingRooms, generalRooms,teachingRooms, instructors);
                    break;
                case 2:
                    // Directly call the login function
                    login(users);
                    break;
                case 3:
                    Admin.adminLogin(users,meetingRooms, generalRooms,teachingRooms, instructors);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }while (choice > 3);
    }
}