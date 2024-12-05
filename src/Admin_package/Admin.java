
package Admin_package;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Rooms.Room;
import Rooms.MeetingRooms;
import Rooms.Slot;
import java.util.ArrayList;
import java.util.Date;
import Rooms.GeneralRooms;
import Rooms.TeachingRooms;
import Visitors.Instructor;
import Visitors.General;
import Visitors.Formal;


public class Admin {
    private String name;
    private String password;
    private final Scanner input = new Scanner(System.in);

    // Constructor
    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Add slots to a specific room type
    public void addSlots() {
        try {
            System.out.print("Enter the number of slots you would like to add: ");
            int numOfSlots = Integer.parseInt(input.nextLine());

            for (int i = 0; i < numOfSlots; i++) {
                System.out.print("Adding slot #" + (i + 1) + " of " + numOfSlots + ". Enter the room type (1 for General Room, 2 for Meeting Room, 3 for Teaching Room): ");
                boolean validInput = false;

                while (!validInput) {
                    String roomType = input.nextLine();
                    switch (roomType) {
                        case "1":
                            System.out.print("Adding slot to General Room... ");
                            inputAddSlot("General Room");
                            validInput = true;
                            break;
                        case "2":
                            System.out.print("Adding slot to Meeting Room... ");
                            inputAddSlot("Meeting Room");
                            validInput = true;
                            break;
                        case "3":
                            System.out.print("Adding slot to Teaching Room... ");
                            inputAddSlot("Teaching Room");
                            validInput = true;
                            break;
                        default:
                            System.out.print("Invalid input. Please enter 1, 2, or 3: ");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for the number of slots.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Input slot details for a specific room type
    private void inputAddSlot(String roomType) {
        LocalDate date;
        LocalTime time;
        double fee;

        // Input and validate date
        while (true) {
            try {
                System.out.print("Enter date (yyyy-MM-dd): ");
                String dateInput = input.nextLine();
                date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Error: The date must not be in the past.");
                } else {
                    break; // Valid date
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        // Input and validate time
        while (true) {
            try {
                System.out.print("Enter time (HH:mm): ");
                String timeInput = input.nextLine();
                time = LocalTime.parse(timeInput, DateTimeFormatter.ISO_LOCAL_TIME);
                break; // Valid time
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Please use HH:mm.");
            }
        }

        // Input and validate fee
        while (true) {
            try {
                System.out.print("Enter fee: ");
                fee = Double.parseDouble(input.nextLine());
                if (fee < 50) {
                    System.out.println("Error: Fee cannot be less than 50.");
                } else {
                    break; // Valid fee
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Fee must be a valid number.");
            }
        }

        // Print confirmation of added slot
        System.out.println("Slot added successfully to " + roomType + ":");
        System.out.println("Date: " + date + ", Time: " + time + ", Fee: $" + fee);


    }
}