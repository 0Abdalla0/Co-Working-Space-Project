
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
    public void addSlots(Room room) {
        room.inputAddSlot();
    }

    // Input slot details for a specific room type


        //Room object.slots.add(newSlot);
        // Print confirmation of added slot
//        System.out.println("Slot added successfully to " + roomType + ":");
//        System.out.println("Date: " + date + ", Time: " + time + ", Fee: $" + fee);


    }
