package User;

import Admin_package.Admin;
import Rooms.*;
import Visitors.Formal;
import Visitors.General;
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
    public static Visitor register(ArrayList<Visitor> visitors) {
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
                    return new Formal(name, password,visitorType);
                case 2:
                    visitorType="General";
                    return new General(name, password,visitorType);

                case 3:
                    visitorType="Instructor";
                    return new Instructor(name, password,visitorType);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        // Create a new User object and return it
        return new Visitor(name, password,visitorType);


    }


    public static void login(ArrayList<Visitor> visitors, ArrayList<Room> teachingRooms, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Instructor> instructors) {
            String nameLogin ="", passwordLogin="";

        try {
            System.out.println("Login Page\n");
            System.out.println("Enter your Name:");
            nameLogin = input.next();
            System.out.println("Enter password:");
            passwordLogin = input.next();
        }catch (Exception e) {
            System.out.println("Login Error : "+e.getMessage());
            input.nextLine();
        }
        boolean loggedIn = false; // Reset loggedIn for each login attempt

        for (Visitor v : visitors) {
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
                v.options(teachingRooms, meetingRooms, generalRooms, visitors, instructors);
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
                        login(visitors, teachingRooms, meetingRooms, generalRooms, instructors);
                        return; // Avoid further execution
                    case 2:
                        Visitor newVisitor = register(visitors);
                        visitors.add(newVisitor);
                        return; // Avoid further execution
                }
            }
        }
    }

    // Menu for starting the application
    public static void startMenu(ArrayList<Visitor> visitors, ArrayList<Room>meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {

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
                    Visitor newVisitor = register(visitors);
                    visitors.add(newVisitor);
                    startMenu(visitors,meetingRooms, generalRooms,teachingRooms, instructors);
                    break;
                case 2:
                    // Directly call the login function
                    login(visitors, teachingRooms, meetingRooms, generalRooms, instructors);
                    break;
                case 3:
                    Admin.adminLogin(visitors,meetingRooms, generalRooms,teachingRooms, instructors);
                    break;
                case 4:
                    startMenu(visitors,meetingRooms, generalRooms, teachingRooms, instructors);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }while (choice > 3);
    }
}