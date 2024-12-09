package Rooms;
import Visitors.Visitor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Room {
    protected String name;
    protected int ID, number_of_visitors;
    private ArrayList<Slot> Availableslots = new ArrayList<>();
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
                System.out.println("Enter the total hours (can be fractional): ");
                String totalHoursInput = input.nextLine();
                double totalHours = Double.parseDouble(totalHoursInput);
                // Convert fractional hours to minutes
                long minutesToAdd = (long) (totalHours * 60); // Convert hours to minutes
                // Add the minutes to the start time
                endTime = startTime.plusMinutes(minutesToAdd);

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
        Slot newSlot = new Slot(date, startTime,endTime,fee);
        Availableslots.add(newSlot);
        System.out.print("Added successfully ");

    }

    public void displayAvailableSlots(){
        System.out.println("Available slots:");
        for(Slot slot : Availableslots){
            System.out.println("start time : " + slot.getStartTime() + " end time : " + slot.getEndTime());
        }
    }
}



  //  public abstract void addVisitor(String visitor);
//}



