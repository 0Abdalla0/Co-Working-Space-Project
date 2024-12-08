
package Admin_package;
import Rooms.Room;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import User.user;


public class Admin {
    private static String name;
    private static String password;
    public static Scanner input = new Scanner(System.in);

    // Constructor
    public Admin() {
    }


    public static void adminLogin(ArrayList<user> users) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Admin Name: ");
            String name = input.nextLine();
            System.out.println("Enter Admin Password: ");
            String password = input.nextLine();
            if (name.equals("admin") && password.equals("admin")) {
                System.out.println("Admin Login Successful");
                Admin.options(users);
                break;

            } else {
                System.out.println("Admin Login Failed, try again");
            }
        }
    }

    // Add slots to a specific room type
    public static void addSlots(Room room) {
        int num_of_slots;
        System.out.println("Enter Number of Slots you want to add: ");
        num_of_slots = input.nextInt();
        for (int i = 0; i < num_of_slots; i++) {
            room.inputAddSlot();

        }
    }

    // Input slot details for a specific room type


    //Room object.slots.add(newSlot);
    // Print confirmation of added slot
//        System.out.println("Slot added successfully to " + roomType + ":");
//        System.out.println("Date: " + date + ", Time: " + time + ", Fee: $" + fee);
    public static void options(ArrayList<user> users) {
        boolean retry = false;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("---------------ADMIN MENU---------------");
            System.out.println("1. Add Slots\n2. Delete specific entity\n3. Display all slots\n4. Display all visitors");
            System.out.println("5.Display all rooms data\n6.Display all instructors data\n7. calculate and display total amount of money for all rooms\n8. update any entity ");
            System.out.println("Enter your choice: ");
            int option = input.nextInt();
            switch (option) {
                case 1:

                case 2:
                    Admin.delete_entity(users);
                    break;







            }
            try {
                System.out.println("Do You Want To Choose Another Service? (Y/N)");
                String choose = input.next();
                if (choose.equalsIgnoreCase("Y")) {
                    retry = true;
                } else if (choose.equalsIgnoreCase("N")) {
                    retry = false;
                }
            }
            catch (Exception e) {
                System.out.println("Invalid Option");
            }
        } while (retry);
    }
    public static void delete_entity(ArrayList<user> users) {
        boolean continueDeleting = true;
        while (continueDeleting) {
            System.out.println("Select entity to delete: ");
            System.out.println("1. Room");
            System.out.println("2. Visitor");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter Room ID to delete: ");
                    int room_id = input.nextInt();
                    // display room list with name id
                    //delete_room(room_id)
                    break;
                case 2:
                    System.out.print("Enter Visitor ID to delete: ");
                    int visitor_id = input.nextInt();
                    // display user list with name w id
                    delete_visitor(visitor_id,users);
                    break;

                default:
                    System.out.println("Invalid Option. try again");


            }
            System.out.println("Do You Want To continue deleting?");
            String choose = input.next();
            if (choose.equalsIgnoreCase("Y")) {
                continueDeleting = true;
            }
            else{
                continueDeleting = false;
            }
        }
    }
    public static void delete_room(int room_id, ArrayList<Room> rooms) {
        boolean found = false;
        while (!found) {
            for (Room room : rooms) {
                if(room_id == room.getID())
                {
                    rooms.remove(room);
                    System.out.println("Deleted Room ID: " + room_id );
                    found = true;
                    break;
                }
            }
            if(found)
                break;
            else{
                System.out.println("Invalid Room ID. try again");
                System.out.println("Enter correct id to delete");
                room_id = input.nextInt();
            }
        }
    }
    public static void delete_visitor(int visitor_id, ArrayList<user> users) {
        boolean found = false;
        while (!found) {
            for (user user : users) {
                if(visitor_id == user.getId())
                {
                    users.remove(user);
                    System.out.println("visitor with id " + visitor_id + " deleted successfully");
                    found = true;
                    break;
                }
            }
            if(found)
                break;
            else{
                System.out.println("not found");
                System.out.println("Enter correct id to delete");
                visitor_id = input.nextInt();
            }
        }
    }
}
