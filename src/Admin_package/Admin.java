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


    public static void adminLogin(ArrayList<user> users,ArrayList<Room> rooms,ArrayList<Slot> slots) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Admin Name: ");
            String name = input.nextLine();
            System.out.println("Enter Admin Password: ");
            String password = input.nextLine();
            if (name.equals("admin") && password.equals("admin")) {
                System.out.println("Admin Login Successful");
                Admin.options(users,rooms,slots);
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
    public static void options(ArrayList<user> users,ArrayList<Room> rooms,ArrayList<Slot> slots) {
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
                    update_entity(users,rooms,slots);

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
                     update_room(rooms,room_id);
                    break;
                case 2:
                    // display users list with name w id
                    System.out.print("Enter Visitor ID you want update: ");
                    int visitor_id = input.nextInt();
                    // display the user id name,pass
                    update_visitor(users,visitor_id);
                    break;
                case 3:
                    // display all slots
                    updateSlot(slots);


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
                    if (room.getID() == id) {
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
                        targetRoomId.setID(newId); //
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
    public static void updateSlot( ArrayList<Slot> slots) {
        Scanner input = new Scanner(System.in);
        LocalDate date;
        LocalTime startTime;
        Slot targetSlot = null;

        // Input and validate date
        while (true) {
            try {
                System.out.print("Enter date of the slot to update (yyyy-MM-dd): ");
                String dateInput = input.nextLine();
                date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                break; // Valid date
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        // Input and validate start time
        while (true) {
            try {
                System.out.print("Enter start time of the slot to update (HH:mm): ");
                String timeInput = input.nextLine();
                startTime = LocalTime.parse(timeInput, DateTimeFormatter.ISO_LOCAL_TIME);

                // Find the slot by date and start time
                for (Slot slot : slots) {
                    if (slot.getDate().equals(date) && slot.getStartTime().equals(startTime)) {
                        targetSlot = slot;
                        break;
                    }
                }

                if (targetSlot == null) {
                    System.out.println("Error: No slot found with the given date and start time.");
                    return;
                }

                break; // Slot found
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Please use HH:mm.");
            }
        }

        // Menu for updating slot details
        System.out.println("What do you want to update?");
        System.out.println("1. Date");
        System.out.println("2. Start time");
        System.out.println("3. End time");
        System.out.println("4. Fees");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                // Update date
                while (true) {
                    try {
                        System.out.print("Enter new date (yyyy-MM-dd): ");
                        String newDateInput = input.nextLine();
                        LocalDate newDate = LocalDate.parse(newDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

                        if (newDate.isBefore(LocalDate.now())) {
                            System.out.println("Error: The date must not be in the past.");
                        } else {
                            targetSlot.setDate(newDate);
                            System.out.println("Slot date updated successfully.");
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
                break;

            case 2:
                // Update start time
                while (true) {
                    try {
                        System.out.print("Enter new start time (HH:mm): ");
                        String newStartTimeInput = input.nextLine();
                        LocalTime newStartTime = LocalTime.parse(newStartTimeInput, DateTimeFormatter.ISO_LOCAL_TIME);

                        // Check for conflicts
                        boolean conflict = false;
                        for (Slot slot : slots) {
                            if (slot.getDate().equals(date) && slot.getStartTime().equals(newStartTime)) {
                                conflict = true;
                                break;
                            }
                        }

                        if (conflict) {
                            System.out.println("Error: A slot with the new start time already exists on the same date.");
                        } else {
                            targetSlot.setStartTime(newStartTime);
                            System.out.println("Slot start time updated successfully.");
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid time format. Please use HH:mm.");
                    }
                }
                break;

            case 3:
                // Update end time
                while (true) {
                    try {
                        System.out.print("Enter new end time (HH:mm): ");
                        String newEndTimeInput = input.nextLine();
                        LocalTime newEndTime = LocalTime.parse(newEndTimeInput, DateTimeFormatter.ISO_LOCAL_TIME);

                        if (newEndTime.isBefore(targetSlot.getStartTime())) {
                            System.out.println("Error: End time must be after the start time.");
                        } else {
                            targetSlot.setEndTime(newEndTime);
                            System.out.println("Slot end time updated successfully.");
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid time format. Please use HH:mm.");
                    }
                }
                break;

            case 4:
                // Update fees
                while (true) {
                    try {
                        System.out.print("Enter new fees: ");
                        double newFees = Double.parseDouble(input.nextLine());

                        if (newFees < 50) {
                            System.out.println("Error: Fee cannot be less than 50.");
                        } else {
                            targetSlot.setFees(newFees);
                            System.out.println("Slot fees updated successfully.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Fees must be a valid number.");
                    }
                }
                break;

            default:
                System.out.println("Error: Invalid choice.");
        }
    }





}