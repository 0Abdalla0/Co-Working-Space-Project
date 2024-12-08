
package Admin_package;
import Rooms.Room;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Admin {
    private static String name;
    private static String password;
    private Scanner input = new Scanner(System.in);

    // Constructor
    public Admin() {
    }



    public static void adminLogin()
    {
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Admin Name: ");
            String name = input.nextLine();
            System.out.println("Enter Admin Password: ");
            String password = input.nextLine();
            if (name.equals("admin") && password.equals("admin"))
            {
                System.out.println("Admin Login Successful");
                break;

            }
            else{
                System.out.println("Admin Login Failed, try again");
            }
        }
    }

    // Add slots to a specific room type
    public void addSlots(Room room) {
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


    }
