import Admin_package.Admin;
import java.util.ArrayList;
import java.util.Scanner;

public class user {
    static Scanner input = new Scanner(System.in);
    public static int idStatic = 1;
    private String name;
    private String password;
    private String visitorType;
    private int id;

    // Constructor
    public user(String name, String password,String visitorType) {
        this.name = name;
        this.password = password;
        this.id = idStatic;
        this.visitorType = visitorType;
        idStatic++;  // Increment the static id for the next user
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

    public int getId() {
        return id;
    }

    // Register method to add a user
    public static user register() {
        System.out.println("Enter the name of the user you want to add:");
        String name = input.next();
        System.out.println("Enter the password of the user you want to add:");
        String password = input.next();
        int choice=0;
        String visitorType="none";
        while(choice!=1&&choice!=2&&choice!=3)
        {
            System.out.println("Choose \n1.Formal\n2.General\n3.Insructor");
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
        return new user(name, password,visitorType);
    }

    // Login method to validate user credentials
    public static void login(ArrayList<user> users) {
        String nameLogin, passwordLogin;
        System.out.println("Enter the name of the user you want to login:");
        nameLogin = input.next();
        System.out.println("Enter the password of the user you want to login:");
        passwordLogin = input.next();

        boolean loggedIn = false;
        // Search for the user in the list
        for (user u : users) {
            if (u.getName().equals(nameLogin) && u.getPassword().equals(passwordLogin)) {
                System.out.println("You logged in successfully.");
                loggedIn = true;
                break;
            }
        }

        if (!loggedIn) {
            System.out.println("Wrong login or password. Please try again.");
            // Allow the user to retry login without recursion
            login(users);
        }
    }

    // Menu for starting the application
    public static void startMenu(ArrayList<user> users) {
        int choice = 0;
        do {
            System.out.println("\t\t\t\t\t\t\t\t\t***Hello to Galacticos Work space***");
            System.out.println("1. Create a new user \n2. Login (Already have an account)");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Create a new user and add it to the list
                    user newUser = register();
                    users.add(newUser);
                    break;
                case 2:
                    // Directly call the login function
                    login(users);
                    break;
                case 3:
                    Admin.adminLogin();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }while (choice > 3);
    }
}
