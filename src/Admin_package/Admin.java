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

    public static LocalTime getTimeInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String timeInput = input.nextLine();
            try {
                return LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception e) {
                System.out.println("Invalid time! Please use format HH:mm.");
            }
        }
    }

    public static LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String date = input.nextLine();
            try {
                LocalDate resDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (resDate.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("Date cannot be in the past.");
                }
                return resDate;
            } catch (Exception e) {
                System.out.println("Invalid date! Please use format YYYY-MM-DD and ensure the date is not in the past.");
            }
        }
    }


    public static void adminLogin(ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Admin Name: ");
            String name = input.nextLine();
            System.out.println("Enter Admin Password: ");
            String password = input.nextLine();
            if (name.equals("admin") && password.equals("admin")) {
                System.out.println("Admin Login Successful");
                Admin.options(users, meetingRooms, generalRooms, teachingRooms);
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
            System.out.println("Slot " + (i + 1) + ": ");
            room.inputAddSlot();
        }
    }

    // Input slot details for a specific room type


    //Room object.slots.add(newSlot);
    // Print confirmation of added slot
//        System.out.println("Slot added successfully to " + roomType + ":");
//        System.out.println("Date: " + date + ", Time: " + time + ", Fee: $" + fee);
    public static void options(ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
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
                            Admin.addSlots(generalRooms.getFirst()); // general1
                            break;
                        case 2:
                            Admin.addSlots(generalRooms.get(1)); //general2
                            break;
                        case 3:
                            Admin.addSlots(meetingRooms.getFirst()); //meeting1
                            break;
                        case 4:
                            Admin.addSlots(meetingRooms.get(1)); // meeting2
                            break;
                        case 5:
                            Admin.addSlots(meetingRooms.get(2)); // meeting3
                            break;
                        case 6:
                            Admin.addSlots(teachingRooms.getFirst()); // teaching1
                            break;
                        case 7:
                            Admin.addSlots(teachingRooms.get(1)); // teaching2
                            break;
                        case 8:
                            Admin.addSlots(teachingRooms.get(2)); // teaching3
                            break;
                    }
                    break;

                case 2:
                    Admin.delete_entity(users, meetingRooms, generalRooms, teachingRooms);
                    break;

                case 3:
                    //Room.displayAvailableSlots();
                    break;

                case 4:
                    //display visitors data function

                case 5:
                    // display all rooms data function

                case 6:
                    // calc money and display for all rooms

                case 7:
                    update_entity(users, meetingRooms, generalRooms, teachingRooms);

            }
            try {
                System.out.println("Do You Want To Choose Another Service? (Y/N)");
                String choose = input.next();
                if (choose.equalsIgnoreCase("Y")) {
                    retry = true;
                } else if (choose.equalsIgnoreCase("N")) {
                    retry = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid Option");
            }
        } while (retry);
    }

    public static void delete_entity(ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
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
                    delete_room(room_id, meetingRooms, generalRooms, teachingRooms);
                    break;
                case 2:
                    System.out.print("Enter Visitor ID to delete: ");
                    int visitor_id = input.nextInt();
                    // display user list with name w id
                    delete_visitor(visitor_id, users);
                    break;

                default:
                    System.out.println("Invalid Option. try again");


            }
            System.out.println("Do You Want To continue deleting?");
            String choose = input.next();
            if (choose.equalsIgnoreCase("Y")) {
                continueDeleting = true;
            } else {
                continueDeleting = false;
            }
        }
    }

    public static void delete_room(int room_id, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean found = false;
        for (Room room : meetingRooms) {
            if (room_id == room.getID()) {
                meetingRooms.remove(room);
                System.out.println("Deleted Room ID: " + room_id + " from Meeting Rooms");
                found = true;
                break;
            }
        }

        // Search and delete from generalRooms if not found
        if (!found) {
            for (Room room : generalRooms) {
                if (room_id == room.getID()) {
                    generalRooms.remove(room);
                    System.out.println("Deleted Room ID: " + room_id + " from General Rooms");
                    found = true;
                    break;
                }
            }
        }

        // Search and delete from teachingRooms if not found
        if (!found) {
            for (Room room : teachingRooms) {
                if (room_id == room.getID()) {
                    teachingRooms.remove(room);
                    System.out.println("Deleted Room ID: " + room_id + " from Teaching Rooms");
                    found = true;
                    break;
                }
            }
        }

        // If room is not found, print a message
        if (!found) {
            System.out.println("Room ID " + room_id + " not found in any list.");
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

    public static void update_entity(ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
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
                    update_room(meetingRooms, generalRooms, teachingRooms);
                    break;
                case 2:
                    // display users list with name w id
                    System.out.print("Enter Visitor ID you want update: ");
                    int visitor_id = input.nextInt();
                    // display the user id name,pass
                    update_visitor(users,visitor_id);
                    break;
                case 3:
                    updateSlot(meetingRooms, generalRooms, teachingRooms);
//                    LocalDate dateTest = getDateInput("enter date test");
//                    System.out.println(meetingRooms.get(0).displayAvailableSlots(dateTest));


                default:
                    System.out.println("Invalid Option. try again");


            }
            System.out.println("Do You Want To continue updating?");
            String choose = input.next();
            if (choose.equalsIgnoreCase("Y")) {
                continueUpdating = true;
            } else {
                continueUpdating = false;
            }
        }
    }

    public static void update_room(ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean found = false;


        while (!found) {

            System.out.print("Enter the ID of the room to update: ");
            int id = input.nextInt();
            // display room name and id

            Room targetRoom = null;

            // Search in meetingRooms
            for (Room room : meetingRooms) {
                if (room.getID() == id) {
                    targetRoom = room;
                    break;
                }
            }

            // Search in generalRooms if not found
            if (targetRoom == null) {
                for (Room room : generalRooms) {
                    if (room.getID() == id) {
                        targetRoom = room;
                        break;
                    }
                }
            }

            // Search in teachingRooms if not found
            if (targetRoom == null) {
                for (Room room : teachingRooms) {
                    if (room.getID() == id) {
                        targetRoom = room;
                        break;
                    }
                }
            }

            // If the room is found, proceed to update
            if (targetRoom != null) {
                found = true;

                System.out.println("Choose 1 to update room name, or 2 to update room ID:");
                int choice = input.nextInt();

                switch (choice) {
                    case 1: // Update room name
                        System.out.print("Enter new room name to update: ");
                        String roomName = input.next();
                        targetRoom.setName(roomName);
                        System.out.println("Room name updated successfully.");
                        break;

                    case 2: // Update room ID
                        System.out.print("Enter the new room ID to update: ");
                        int newId = input.nextInt();

                        // Check if the new ID already exists in any list
                        boolean idExists = false;

                        for (Room room : meetingRooms) {
                            if (room.getID() == newId) {
                                idExists = true;
                                break;
                            }
                        }
                        for (Room room : generalRooms) {
                            if (room.getID() == newId) {
                                idExists = true;
                                break;
                            }
                        }
                        for (Room room : teachingRooms) {
                            if (room.getID() == newId) {
                                idExists = true;
                                break;
                            }
                        }

                        if (!idExists) {
                            targetRoom.setID(newId);
                            System.out.println("Room ID updated successfully.");
                        } else {
                            System.out.println("The entered ID is already in use. Please try again with a different ID.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // If the room is not found, prompt the user to try again
                System.out.println("Room with the given ID not found. Please try again.");
            }
        }
    }

    public static void update_visitor(ArrayList<user> users, int id) {
        System.out.println("Choose what you want to update: 1 for name, 2 for ID, 3 for password");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                // Update visitor name
                user targetUser = null;
                for (user user : users) {
                    if (user.getId() == id) {
                        targetUser = user;
                        break;
                    }
                }

                if (targetUser != null) {
                    System.out.print("Enter new name: ");
                    String newName = input.next();
                    targetUser.setName(newName);
                    System.out.println("Visitor name updated successfully.");
                } else {
                    System.out.println("Visitor with the given ID not found.");
                }
                break;

            case 2:
                // Update visitor ID
                user targetUserForId = null;
                for (user user : users) {
                    if (user.getId() == id) {
                        targetUserForId = user;
                        break;
                    }
                }

                if (targetUserForId != null) {
                    System.out.print("Enter new ID: ");
                    int newId = input.nextInt();

                    // Check if the new ID already exists
                    boolean idExists = false;
                    for (user user : users) {
                        if (user.getId() == newId) {
                            idExists = true;
                            break;
                        }
                    }

                    if (!idExists) {
                        targetUserForId.setId(newId);
                        System.out.println("Visitor ID updated successfully.");
                    } else {
                        System.out.println("The entered ID is already in use. Please try again with a different ID.");
                    }
                } else {
                    System.out.println("Visitor with the given ID not found.");
                }
                break;

            case 3:
                // Update visitor password
                user targetUserForPassword = null;
                for (user user : users) {
                    if (user.getId() == id) {
                        targetUserForPassword = user;
                        break;
                    }
                }

                if (targetUserForPassword != null) {
                    System.out.print("Enter new password: ");
                    String newPassword = input.next();
                    targetUserForPassword.setPassword(newPassword);
                    System.out.println("Visitor password updated successfully.");
                } else {
                    System.out.println("Visitor with the given ID not found.");
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void updateSlot(ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean found = false;


        while (!found) {

            System.out.print("Enter the ID of the room to update: ");
            int id = input.nextInt();
            // display room name and id

            Room targetRoom = null;

            // Search in meetingRooms
            for (Room search : meetingRooms) {
                if (search.getID() == id) {
                    targetRoom = search;
                    break;
                }
            }

            // Search in generalRooms if not found
            if (targetRoom == null) {
                for (Room search : generalRooms) {
                    if (search.getID() == id) {
                        targetRoom = search;
                        break;
                    }
                }
            }

            // Search in teachingRooms if not found
            if (targetRoom == null) {
                for (Room search : teachingRooms) {
                    if (search.getID() == id) {
                        targetRoom = search;
                        break;
                    }
                }
            }
            if (targetRoom != null) {
                found = true;
                targetRoom.updateSlot();
            }
        }
    }
}