package Rooms;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Room {
    protected String name;
    protected int ID, number_of_visitors;
    private ArrayList<Slot> slots = new ArrayList<>();
//    private ArrayList<Visitor> visitors = new ArrayList<>();


    public Room(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public void inputAddSlot() {
        Scanner input = new Scanner(System.in);
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
        Slot newSlot = new Slot(date, time,time,fee);
        slots.add(newSlot);
        System.out.print("Added successfully ");

    }
}

  //  public abstract void addVisitor(String visitor);
//}



