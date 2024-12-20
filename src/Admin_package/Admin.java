package Admin_package;
import Rooms.*;
import java.util.InputMismatchException;
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
    public static Scanner input = new Scanner(System.in);
    public Admin(String name, String password, String visitorType, int i) {
        super(name, password, visitorType, i);
    }
//
//    public static LocalTime getTimeInput(String prompt) {
//        while (true) {
//            System.out.println(prompt);
//            String timeInput = input.nextLine();
//            try {
//                return LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
//            } catch (Exception e) {
//                System.out.println("Invalid time! Please use format HH:mm.");
//            }
//        }
//    }
//
//    public static LocalDate getDateInput(String prompt) {
//        while (true) {
//            System.out.println(prompt);
//            String date = input.nextLine();
//            try {
//                LocalDate resDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                if (resDate.isBefore(LocalDate.now())) {
//                    throw new IllegalArgumentException("Date cannot be in the past.");
//                }
//                return resDate;
//            } catch (Exception e) {
//                System.out.println("Invalid date! Please use format YYYY-MM-DD and ensure the date is not in the past.");
//            }
//        }
//    }


    public static void adminLogin(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        while (true) {
            try {
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
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try logging in again.");
            }
        }
    }

    // Add slots to a specific room type
    public static void addSlots(Room room) {
        Scanner input = new Scanner(System.in);
        int num_of_slots = 0;
        boolean validInput = false;

        // Prompt user for number of slots until a valid integer is entered
        do {
            try {
                System.out.println("Enter Number of Slots you want to add: ");
                num_of_slots = input.nextInt();
                input.nextLine(); // Clear the buffer
                if (num_of_slots <= 0) {
                    System.out.println("Please enter a positive integer for the number of slots.");
                } else {
                    validInput = true; // Valid input, exit the loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // Clear invalid input
            }
        } while (!validInput);

        // Add the specified number of slots
        for (int i = 0; i < num_of_slots; i++) {
            try {
                System.out.println("Slot " + (i + 1) + ": ");
                room.inputAddSlot(); // Assuming this method handles slot input
            } catch (Exception e) {
                System.out.println("An error occurred while adding the slot: " + e.getMessage());
                System.out.println("Skipping this slot and continuing...");
            }
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
            try {
                System.out.println("---------------ADMIN MENU---------------");
                System.out.println("1. Add Slots\n2. Delete specific entity\n3. Display all slots\n4. Display all visitors");
                System.out.println("5. Display all instructors data\n6. Display all rooms data");
                System.out.println("7. Calculate and display total amount of money for all rooms\n8. Update any entity");
                System.out.println("Enter your choice: ");

                int option = input.nextInt();
                switch (option) {
                    case 1:
                        boolean validInput = false;
                        do {
                            try {
                                System.out.println("Enter which room you want to add slots in: ");
                                System.out.println("1. General #1\n2. General #2\n3. Meeting #1\n4. Meeting #2");
                                System.out.println("5. Meeting #3\n6. Teaching #1\n7. Teaching #2\n8. Teaching #3");

                                int addRoom = input.nextInt();
                                validInput = true; // Assume input is valid unless proven otherwise

                                switch (addRoom) {
                                    case 1:
                                        Admin.addSlots(generalRooms.get(0)); // general1
                                        break;
                                    case 2:
                                        Admin.addSlots(generalRooms.get(1)); // general2
                                        break;
                                    case 3:
                                        Admin.addSlots(meetingRooms.get(0)); // meeting1
                                        break;
                                    case 4:
                                        Admin.addSlots(meetingRooms.get(1)); // meeting2
                                        break;
                                    case 5:
                                        Admin.addSlots(meetingRooms.get(2)); // meeting3
                                        break;
                                    case 6:
                                        Admin.addSlots(teachingRooms.get(0)); // teaching1
                                        break;
                                    case 7:
                                        Admin.addSlots(teachingRooms.get(1)); // teaching2
                                        break;
                                    case 8:
                                        Admin.addSlots(teachingRooms.get(2)); // teaching3
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        validInput = false; // Reset validInput for out-of-bounds input
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                                input.next(); // Clear invalid input
                            }
                        } while (!validInput);
                        break;

                    case 2:
                        Admin.delete_entity(visitors, meetingRooms, generalRooms, teachingRooms);
                        break;

                    case 3:
//                        Room.displayAvailableSlots();
                        break;

                    case 4:
                        for (Visitor v : visitors) {
                            System.out.println(v);
                        }
                        break;

                    case 5:
                        for (Instructor i : instructors) {
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
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                System.out.println("Do You Want To Choose Another Service? (Y/N)");
                String choose = input.next();
                if (choose.equalsIgnoreCase("Y")) {
                    retry = true;
                } else if (choose.equalsIgnoreCase("N")) {
                    retry = false;
                    user.startMenu(visitors, meetingRooms, generalRooms, teachingRooms, instructors);
                } else {
                    System.out.println("Invalid option. Returning to main menu.");
                    retry = false;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid menu option.");
                input.next(); // Clear invalid input
            }
        } while (retry);


    }


    public static void delete_entity(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean continueDeleting = true;
        Scanner input = new Scanner(System.in);

        while (continueDeleting) {
            boolean validSelection = false; // Flag to track if the user made a valid choice
            while (!validSelection) {
                try {
                    System.out.println("Select entity to delete: ");
                    System.out.println("1. Room");
                    System.out.println("2. Visitor");

                    int option = input.nextInt();
                    switch (option) {
                        case 1:
                            Admin.displayAllRooms(generalRooms, meetingRooms, teachingRooms);
                            System.out.print("Enter Room ID to delete: ");
                            int room_id = input.nextInt();
                            delete_room(room_id, meetingRooms, generalRooms, teachingRooms); // Method to delete a room
                            validSelection = true; // Valid choice, exit the loop
                            break;

                        case 2:
                            for (Visitor v : visitors) {
                                System.out.println(v); // Display visitors with their details
                            }
                            System.out.print("Enter Visitor ID to delete: ");
                            int visitor_id = input.nextInt();
                            delete_visitor(visitor_id, visitors); // Method to delete a visitor
                            validSelection = true; // Valid choice, exit the loop
                            break;

                        default:
                            System.out.println("Invalid option. Please select a valid entity.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number corresponding to an option.");
                    input.next(); // Clear invalid input from scanner buffer
                }
            }

            // After successfully deleting, ask if they want to continue
            System.out.println("Do You Want To Continue Deleting? (Y/N)");
            String choose = input.next();
            if (!choose.equalsIgnoreCase("Y")) {
                continueDeleting = false;
            }
        }
    }



    public static void delete_room(int room_id, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        Scanner input = new Scanner(System.in);
        boolean roomDeleted = false; // Flag to track if a room has been deleted

        while (!roomDeleted) {
            boolean found = false;

            // Search and delete from meetingRooms
            for (Room room : meetingRooms) {
                if (room_id == room.getID()) {
                    meetingRooms.remove(room);
                    System.out.println("Deleted Room ID: " + room_id + " from Meeting Rooms");
                    found = true;
                    roomDeleted = true; // Room found and deleted, exit loop
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
                        roomDeleted = true; // Room found and deleted, exit loop
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
                        roomDeleted = true; // Room found and deleted, exit loop
                        break;
                    }
                }
            }

            // If room is not found, prompt the user to re-enter a valid room ID
            if (!found) {
                System.out.println("Room ID " + room_id + " not found in any list.");
                System.out.print("Please enter a valid Room ID: ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a numeric Room ID.");
                    input.next(); // Clear invalid input
                }
                room_id = input.nextInt(); // Accept new room ID
            }
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
        Scanner input = new Scanner(System.in);
        boolean continueUpdating = true;

        while (continueUpdating) {
            int option = 0;
            boolean validInput = false;

            // Loop to get a valid option
            while (!validInput) {
                try {
                    System.out.println("Select entity to update: ");
                    System.out.println("1. Room");
                    System.out.println("2. Visitor");
                    System.out.println("3. Slot");
                    option = input.nextInt();
                    input.nextLine(); // Clear the buffer
                    if (option >= 1 && option <= 3) {
                        validInput = true; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    input.nextLine(); // Clear invalid input
                }
            }

            // Handle the selected option
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
                    break;
                default:
                    // This should not happen due to validation but included as a safeguard
                    System.out.println("Unexpected error. Please try again.");
            }

            // Ask if the user wants to continue updating
            boolean validChoice = false;
            while (!validChoice) {
                try {
                    System.out.println("Do You Want To continue updating? (Y/N)");
                    String choose = input.nextLine().trim();
                    if (choose.equalsIgnoreCase("Y")) {
                        continueUpdating = true;
                        validChoice = true; // Valid choice, exit the loop
                    } else if (choose.equalsIgnoreCase("N")) {
                        continueUpdating = false;
                        validChoice = true; // Valid choice, exit the loop
                    } else {
                        System.out.println("Invalid choice. Please enter 'Y' or 'N'.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }


    public static void update_room(ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        boolean found = false;

        while (!found) {
            try {
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

                    // Loop to ensure valid input for choice
                    boolean validChoice = false;
                    while (!validChoice) {
                        try {
                            System.out.println("Choose 1 to update room name, or 2 to update room ID:");
                            int choice = input.nextInt();

                            switch (choice) {
                                case 1: // Update room name
                                    System.out.print("Enter new room name to update: ");
                                    String roomName = input.next();
                                    targetRoom.setName(roomName);
                                    System.out.println("Room name updated successfully.");
                                    validChoice = true; // Exit the loop
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
                                        validChoice = true; // Exit the loop
                                    } else {
                                        System.out.println("The entered ID is already in use. Please try again with a different ID.");
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please enter 1 or 2.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            input.nextLine(); // Clear invalid input
                        }
                    }
                } else {
                    // If the room is not found, prompt the user to try again
                    System.out.println("Room with the given ID not found. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // Clear invalid input
            }
        }
    }


    public static void update_visitor(ArrayList<Visitor> visitors) {
        boolean found = false;

        while (!found) {
            try {
                System.out.print("Enter Visitor ID you want to update: ");
                int visitor_id = input.nextInt();

                // Search for the visitor by ID
                Visitor targetUser = null;
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

                    // Loop until the user provides a valid choice
                    boolean validChoice = false;
                    while (!validChoice) {
                        try {
                            System.out.println("Choose what you want to update: 1 for Name, 2 for ID, 3 for Password");
                            int choice = input.nextInt();

                            switch (choice) {
                                case 1:
                                    // Update name
                                    System.out.print("Enter new name: ");
                                    String newName = input.next();
                                    targetUser.setName(newName);
                                    System.out.println("Visitor name updated successfully.");
                                    validChoice = true; // Exit the loop
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
                                        validChoice = true; // Exit the loop
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
                                    validChoice = true; // Exit the loop
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            input.nextLine(); // Clear invalid input
                        }
                    }
                } else {
                    // If visitor is not found, prompt user to try again
                    System.out.println("Visitor with the given ID not found. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // Clear invalid input
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
    public static void calcRoom(ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Which type of room do you want to calculate?");
                System.out.println("1. Meeting Room     2. General Room     3. Teaching Room");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        for (Room room : meetingRooms) {
                            room.addFees();
                        }
                        System.out.println("Meeting room fees: ");
                        System.out.println(MeetingRoom.getTotalFees());
                        validInput = true; // Valid choice, exit the loop
                        break;
                    case 2:
                        for (Room room : generalRooms) {
                            room.addFees();
                        }
                        System.out.println("General room fees: ");
                        System.out.println(GeneralRoom.getTotalFees());
                        validInput = true; // Valid choice, exit the loop
                        break;
                    case 3:
                        for (Room room : teachingRooms) {
                            room.addFees();
                        }
                        System.out.println("Teaching room fees: ");
                        System.out.println(TeachingRoom.getTotalFees());
                        validInput = true; // Valid choice, exit the loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // Clear the invalid input from the scanner buffer
            }
        }
    }


}