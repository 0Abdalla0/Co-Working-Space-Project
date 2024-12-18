package Admin_package;
import Rooms.GeneralRoom;
import Rooms.MeetingRoom;
import Rooms.Room;
import Rooms.Slot;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import User.user;
import Visitors.Instructor;
import Visitors.Visitor;


public class Admin extends user {
    private static String name;
    private static String password;
    public static Scanner input = new Scanner(System.in);

    public Admin(String name, String password, String visitorType, int i) {
        super(name, password, visitorType, i);
    }

    // Constructor
//    public Admin() {
//    }

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


    public static void adminLogin(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Admin Name: ");
            String name = input.nextLine();
            System.out.println("Enter Admin Password: ");
            String password = input.nextLine();
            if (name.equals("admin") && password.equals("admin")) {
                System.out.println("Admin Login Successful");
                Admin.options(visitors, meetingRooms, generalRooms, teachingRooms, instructors);
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
    public static void options(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        boolean retry = false;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("---------------ADMIN MENU---------------");
            System.out.println("1. Add Slots\n2. Delete specific entity\n3. Display all slots\n4. Display all visitors\n5. Display all instructors data ");
            System.out.println("6.Display all rooms data\n7. calculate and display total amount of money for all rooms\n8. update any entity ");
            System.out.println("Enter your choice: ");
            int option = input.nextInt();
            //
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
                    Admin.delete_entity(visitors, meetingRooms, generalRooms, teachingRooms);
                    break;

                case 3:
                    //Room.displayAvailableSlots();
                    break;

                case 4:
                    for (Visitor v : visitors) {
                        System.out.println(v);
                    }
                    break;
                case 5:
                    for (Instructor i : instructors){
                        System.out.println(i);
                    }
                    break;

                case 6:
                    Admin.displayAllRooms(generalRooms, meetingRooms, teachingRooms);
                     break;

                case 7:
                    calcRoom(meetingRooms, generalRooms, teachingRooms);
                    break;
                case 8:
                    update_entity(visitors, meetingRooms, generalRooms, teachingRooms);

            }
            try {
                System.out.println("Do You Want To Choose Another Service? (Y/N)");
                String choose = input.next();
                if (choose.equalsIgnoreCase("Y")) {
                    retry = true;
                } else if (choose.equalsIgnoreCase("N")) {
                    retry = false;
                    user.startMenu(visitors, meetingRooms, generalRooms, teachingRooms, instructors);
                }
            } catch (Exception e) {
                System.out.println("Invalid Option");
            }
        } while (retry);
    }

    public static void delete_entity(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean continueDeleting = true;
        while (continueDeleting) {
            System.out.println("Select entity to delete: ");
            System.out.println("1. Room");
            System.out.println("2. Visitor");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    Admin.displayAllRooms(generalRooms, meetingRooms, teachingRooms);
                    System.out.print("Enter Room ID to delete: ");
                    int room_id = input.nextInt();
                    // display room list with name id
                    delete_room(room_id, meetingRooms, generalRooms, teachingRooms);
                    break;
                case 2:
                    for (Visitor v : visitors) {
                        System.out.println(v);
                    }
                    System.out.print("Enter Visitor ID to delete: ");
                    int visitor_id = input.nextInt();
                    // display user list with name w id
                    delete_visitor(visitor_id, visitors);
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

    public static void delete_visitor(int visitor_id, ArrayList<Visitor> visitors) {
        boolean found = false;
        while (!found) {
            for (Visitor visitor : visitors) {
                if (visitor_id == visitor.getId()) {
                    visitors.remove(visitor);
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

    public static void update_entity(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean continueUpdating = true;
        while (continueUpdating) {
            System.out.println("Select entity to update: ");
            System.out.println("1. Room");
            System.out.println("2. Visitor");
            System.out.println("3. slot");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    Admin.displayAllRooms(generalRooms, meetingRooms, teachingRooms);
                    update_room(meetingRooms, generalRooms, teachingRooms);
                    break;
                case 2:
                    for (Visitor v : visitors) {
                        System.out.println(v);
                    }
                    update_visitor(visitors);
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

            Room targetRoom = null;
            String roomType = null; // To store the type of the room

            // Search in meetingRooms
            for (Room room : meetingRooms) {
                if (room.getID() == id) {
                    targetRoom = room;
                    roomType = "Meeting Room";
                    break;
                }
            }

            // Search in generalRooms if not found
            if (targetRoom == null) {
                for (Room room : generalRooms) {
                    if (room.getID() == id) {
                        targetRoom = room;
                        roomType = "General Room";
                        break;
                    }
                }
            }

            // Search in teachingRooms if not found
            if (targetRoom == null) {
                for (Room room : teachingRooms) {
                    if (room.getID() == id) {
                        targetRoom = room;
                        roomType = "Teaching Room";
                        break;
                    }
                }
            }

            // If the room is found, display details and proceed to update
            if (targetRoom != null) {
                found = true;

                // Display the room's details
                System.out.println("Room found:");
                System.out.println("Type: " + roomType);
                System.out.println("ID: " + targetRoom.getID());
                System.out.println("Name: " + targetRoom.getName());

                // Prompt user to choose what to update
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


    public static void update_visitor(ArrayList<Visitor> visitors) {
        boolean found = false;

        while (!found) {
            System.out.print("Enter Visitor ID you want to update: ");
            int visitor_id = input.nextInt();

            // Search for the visitor by ID
            user targetUser = null;
            for (Visitor v : visitors) {
                if (v.getId() == visitor_id) {
                    targetUser = v;
                    break;
                }
            }

            // If visitor is found, display their details and proceed
            if (targetUser != null) {
                found = true;

                // Display visitor details
                System.out.println("Visitor found:");
                System.out.println("ID: " + targetUser.getId());
                System.out.println("Name: " + targetUser.getName());
                System.out.println("Password: " + targetUser.getPassword());

                // Prompt user to choose what to update
                System.out.println("Choose what you want to update: 1 for Name, 2 for ID, 3 for Password");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Update name
                        System.out.print("Enter new name: ");
                        String newName = input.next();
                        targetUser.setName(newName);
                        System.out.println("Visitor name updated successfully.");
                        break;

                    case 2:
                        // Update ID
                        System.out.print("Enter new ID: ");
                        int newId = input.nextInt();

                        // Check if new ID already exists
                        boolean idExists = false;
                        for (Visitor v : visitors) {
                            if (v.getId() == newId) {
                                idExists = true;
                                break;
                            }
                        }

                        if (!idExists) {
                            targetUser.setId(newId);
                            System.out.println("Visitor ID updated successfully.");
                        } else {
                            System.out.println("The entered ID is already in use. Please try again with a different ID.");
                        }
                        break;

                    case 3:
                        // Update password
                        System.out.print("Enter new password: ");
                        String newPassword = input.next();
                        targetUser.setPassword(newPassword);
                        System.out.println("Visitor password updated successfully.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // If visitor is not found, prompt user to try again
                System.out.println("Visitor with the given ID not found. Please try again.");
            }
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
    public static void displayAllRooms(ArrayList<Room> generalRooms, ArrayList<Room> meetingRooms, ArrayList<Room> teachingRooms) {
        System.out.println("=== General Rooms ===");
        if (generalRooms.isEmpty()) {
            System.out.println("No general rooms available.");
        } else {
            for (Room room : generalRooms) {
                System.out.println(room); // Assumes Room class has a toString() method
            }
        }

        System.out.println("\n=== Meeting Rooms ===");
        if (meetingRooms.isEmpty()) {
            System.out.println("No meeting rooms available.");
        } else {
            for (Room room : meetingRooms) {
                System.out.println(room); // Assumes Room class has a toString() method
            }
        }

        System.out.println("\n=== Teaching Rooms ===");
        if (teachingRooms.isEmpty()) {
            System.out.println("No teaching rooms available.");
        } else {
            for (Room room : teachingRooms) {
                System.out.println(room); // Assumes Room class has a toString() method
            }
        }
    }
    public static void calcRoom (ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms){
        System.out.println("Which type of room do you want to calculate?");
        System.out.println("1. Meeting Room     2. General Room     3. Teaching Room");
        int choice = input.nextInt();

        switch (choice){
            case 1:
                for (Room room : meetingRooms) {
                    room.addFees();
                }
                System.out.println("Meeting room fees:  ");
                System.out.println(MeetingRoom.getTotalFees());
                break;
            case 2:
                for (Room room : generalRooms) {
                    room.addFees();
                }
                System.out.println("General room fees:  ");
                System.out.println(GeneralRoom.getTotalFees());
                break;
            case 3:
                for (Room room : teachingRooms) {
                    room.addFees();
                }
                System.out.println("Teaching room fees:  ");
                System.out.println(GeneralRoom.getTotalFees());
                break;
        }
    }

}