
package Admin_package;
import Rooms.GeneralRoom;
import Rooms.Room;
import Rooms.Slot;
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
            System.out.println("5.Display all rooms data\n6. calculate and display total amount of money for all rooms\n7. update any entity ");
            System.out.println("Enter your choice: ");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter which room you want to add slots in : ");
                    System.out.println("1. General #1\n2. General #2\n3. Meeting #1\n4. Meeting #2");
                    System.out.println("5. Meeting #3\n6. Teaching #1\n7. Teaching #2\n8. Teaching #3");
                    int addRoom = input.nextInt();
                    switch (addRoom) {
                        case 1:
                            // Admin.addSlots(general1);
                        case 2:
                            // Admin.addSlots(general2);
                        case 3:
                            // Admin.addSlots(meeting1);
                        case 4:
                            // Admin.addSlots(meeting2);
                        case 5:
                            // Admin.addSlots(meeting3);
                        case 6:
                            // Admin.addSlots(teaching1);
                        case 7:
                            // Admin.addSlots(teaching2);
                        case 8:
                            // Admin.addSlots(teaching3);

                    }

                case 2:
                    Admin.delete_entity(users);
                    break;

                case 3:
                    //display all slots function

                case 4:
                    //display visitors data function

                case 5:
                    // display all rooms data function

                case 6:
                    // calc money and display for all rooms

                case 7:






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
                if (visitor_id == user.getId()) {
                    users.remove(user);
                    System.out.println("visitor with id " + visitor_id + " deleted successfully");
                    found = true;
                    break;
                }
            }
            if (found)
                break;
            else {
                System.out.println("not found");
                System.out.println("Enter correct id to delete");
                visitor_id = input.nextInt();
            }
        }
    }
    public static void update_entity(ArrayList<user> users, ArrayList<Room> rooms, ArrayList<Slot> slots) {
        boolean continueUpdating = true;
        while (continueUpdating) {
            System.out.println("Select entity to update: ");
            System.out.println("1. Room");
            System.out.println("2. Visitor");
            System.out.println("3. slot");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    // display room list with name id
                    System.out.print("Enter Room ID to update: ");
                    int room_id = input.nextInt();
                    // display room name and id
                    // update_room(rooms,id);
                    break;
                case 2:
                    // display user list with name w id
                    System.out.print("Enter Visitor ID you want update: ");
                    int visitor_id = input.nextInt();
                    System.out.print("Enter what you want to update from visitor info: ");
                    System.out.println("1. name");
                    System.out.println("2. password");
                    System.out.println("3. id");
                    System.out.println("4. type");
                    int type = input.nextInt();
                    switch (type) {
                        case 1:

                    }

                    break;

                default:
                    System.out.println("Invalid Option. try again");


            }
            System.out.println("Do You Want To continue deleting?");
            String choose = input.next();
            if (choose.equalsIgnoreCase("Y")) {
                continueUpdating = true;
            }
            else{
                continueUpdating = false;
            }
        }
    }
    public static void update_room(ArrayList<Room> rooms, int id) {
        System.out.println("Choose 1 if you want to update room name, or 2 for room id ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                // Find the room by ID
                Room targetRoom = null;
                for (Room room : rooms) {
                    if (room.getID() == id) {
                        targetRoom = room;
                        break;
                    }
                }

                if (targetRoom != null) {
                    System.out.print("Enter new room name to update: ");
                    String room_name = input.next();
                    targetRoom.setName(room_name);
                    System.out.println("Room name updated successfully.");
                } else {
                    System.out.println("Room with the given ID not found.");
                }
                break;


            case 2:
                // Find the room by current ID
                Room targetRoomId = null;
                for (Room room : rooms) {
                    if (room.getID() == id) { // Assuming getId() is a method in your Room class
                        targetRoom = room;
                        break;
                    }
                }

                if (targetRoomId != null) {
                    System.out.print("Enter the new room ID to update: ");
                    int newId = input.nextInt();

                    // Check if the new ID already exists in the list
                    boolean idExists = false;
                    for (Room room : rooms) {
                        if (room.getID() == newId) {
                            idExists = true;
                            break;
                        }
                    }

                    if (!idExists) {
                        targetRoomId.setID(newId); // Assuming setId() is a method in your Room class
                        System.out.println("Room ID updated successfully.");
                    } else {
                        System.out.println("The entered ID is already in use. Please try again with a different ID.");
                    }
                } else {
                    System.out.println("Room with the given ID not found.");
                }
                break;



            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }



}
