package Visitors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Rooms.Room;
import Rooms.Slot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

abstract public class Visitor {
    private String name;
    private String password;
    private final int id;
    private String visitorType;

    public String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    int getId() {
        return id;
    }
    void setPassword(String password) {
        this.password = password;
    }
    void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }
    public String getVisitorType() {
        return visitorType;
    }

    public Visitor(String name, String password, int id, String visitorType) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.visitorType = visitorType;
    }
    private static final Scanner input = new Scanner(System.in);

    //Inputs Date
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

    //Inputs Time
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

    public  void makeRes() {
        System.out.println("How many rooms would you want?");
        int numOfRooms = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numOfRooms; i++) {
            try {
                LocalDate today = LocalDate.now();
                System.out.println("Today's date is" + today);
                LocalDate resDate = getDateInput("Enter The Date You Want (YYYY-MM-DD): ");
                //displayAvailableSlots(resDate);

                LocalTime now = LocalTime.now();
                System.out.println("Current time is" + now);
                LocalTime startTime = getTimeInput("Enter the start time you want (HH:mm): ");
                LocalTime endTime = getTimeInput("Enter the end time you want (HH:mm): ");
                System.out.println("Reservation for room #" + (i + 1) + " on " + resDate + " from " + startTime + "to"+endTime+ " confirmed.");
            } catch (Exception e) {
                System.out.println("Invalid input! Let's try again.");
                i--; // Retry the current room
            }
            //start and end
            //function to reserve the room(time) return room number that is reserved
            //print You have reserved room number #
        }
    }


    void cancelRes(){
        String cont ="";
        System.out.println("******NOTE: THEIR IS A CANCELLATION FEES******\n Do you want to continue (Y/N)");
        cont = input.nextLine();
        boolean retry = true;
        while (retry) {
            if (cont.equalsIgnoreCase("Y")) {
                System.out.println("Enter The Room Number you want to cancel:");
                int numOfRoom = input.nextInt();
                LocalTime time = getTimeInput("Enter start time you want to cancel(HH:mm): ");
                System.out.println("Enter Your Password To Confirm Cancellation: ");
                String password = input.nextLine();
                if (password.equals(this.password)) {

                }
                retry = false;
            } else if (cont.equalsIgnoreCase("N")) {
                options();
                retry = false;
            }else{
                System.out.println("Invalid input! Please try again.");
                retry = true;
            }
        }
    }
    void updateRes(){
        System.out.println("Enter Your Password To Update: ");
        String password = input.nextLine();
        if (!password.equals(this.password)){
            System.out.println("wrong password!!!(try again)");
            updateRes();
        }
        System.out.println("Enter The Room Number you want to update: ");
        LocalTime startTime =getTimeInput("Enter start time you want to update: ");
        int numOfRoom = input.nextInt();

        //check the room is reserved (numOfRoom,startTime)

        System.out.println("What Do You Want To Change? \n1.Room Date\n2.Room Time\n3.Return to Main Menu");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                LocalDate newDate = getDateInput("Enter Your New Date");
                break;
            case 2:
                LocalTime newTime= getTimeInput("Enter Your New Time");
                break;
            case 3:
                options();
                break;
        }
        System.out.println("Displaying Available Rooms...");
        //newDate OR newTime
        //Display
        System.out.println("New Room is reserved And the old is cancelled.");
        //remove from room Old one
        //add new room


    }
    void reward (int TotalHours){}
    //abstract void displayAvailableSlots(LocalDate date);

    public void options() {
        boolean retry = false;
        int option = input.nextInt();
        do {
            switch (option) {
                case 1:
                    makeRes();
                    break;
                case 2:
                    cancelRes();
                    break;
                case 3:
                    updateRes();
                    break;
                //profile

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
}
