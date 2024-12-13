package Rooms;
import Visitors.Visitor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Room {
    protected String name;
    protected int ID, number_of_visitors;
    private static ArrayList<Slot> Availableslots = new ArrayList<>();
    private ArrayList<Slot> ReservedSlots = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();


    public Room(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void inputAddSlot() {
        Scanner input = new Scanner(System.in);
        LocalDate date;
        LocalTime startTime;
        LocalTime endTime;
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
                System.out.print("Enter start time (HH:mm): ");
                String timeInput = input.nextLine();
                startTime = LocalTime.parse(timeInput, DateTimeFormatter.ISO_LOCAL_TIME);

                // Get the total hours to add, allowing fractional input
                System.out.print("Enter total duration in hours (can be fractional): ");
                String totalHoursInput = input.nextLine();
                double totalHours = Double.parseDouble(totalHoursInput);

                // Convert fractional hours to minutes
                long minutesToAdd = (long) (totalHours * 60); // Convert hours to minutes
                endTime = startTime.plusMinutes(minutesToAdd);

                break; // Valid time
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Please use HH:mm.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for the duration.");
            }
        }

        // Input and validate fee
        while (true) {
            try {
                System.out.print("Enter fee (must be at least 50): ");
                fee = Double.parseDouble(input.nextLine());
                if (fee < 50) {
                    System.out.println("Error: Fee must be at least 50.");
                } else {
                    break; // Valid fee
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Fee must be a valid number.");
            }
        }

        // Check for overlap with existing slots
        for (Slot existingSlot : Availableslots) {
            if (existingSlot.getDate().equals(date)) {
                // Check if the new slot overlaps with the existing slot
                if ((startTime.isBefore(existingSlot.getEndTime()) && endTime.isAfter(existingSlot.getStartTime()))) {
                    System.out.println("Error: The new slot overlaps with an existing slot.");
                    return; // Exit without adding the new slot
                }
            }
        }

        // Create the new slot and add it to the available slots list
        Slot newSlot = new Slot(date, startTime, endTime, fee);
        Availableslots.add(newSlot);

        // Provide feedback to the user
        System.out.println("Slot added successfully: " + newSlot);
    }


    public void displayAvailableSlots(){
        System.out.println("Available slots:");
        for(int i = 0; i < Availableslots.size(); i++){
            System.out.println("Slot " + (i+1));
            System.out.println(Availableslots.get(i).getStartTime() + " - " + Availableslots.get(i).getEndTime());
        }
    }


    public void reserveSlot(int index) {
        // Check if the index is valid
        if (index < 0 || index >= Availableslots.size()) {
            System.out.println("Error: Invalid index. No slot available at the given index.");
            return;
        }

        // Retrieve the slot to be reserved
        Slot slot = Availableslots.get(index);

        // Confirm the reservation with details of the slot
        System.out.println("Reserving the following slot:");
        System.out.println("Date: " + slot.getDate());
        System.out.println("Start Time: " + slot.getStartTime());
        System.out.println("End Time: " + slot.getEndTime());
        System.out.println("Fee: " + slot.getFees());

        // Move the slot from available to reserved
        ReservedSlots.add(slot);
        Availableslots.remove(index);

        // Confirm successful reservation
        System.out.println("Slot " + index + " reserved successfully.");
    }

}



  //  public abstract void addVisitor(String visitor);
//}



