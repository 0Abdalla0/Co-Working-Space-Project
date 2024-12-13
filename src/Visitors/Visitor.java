package Visitors;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Rooms.GeneralRoom;
import Rooms.Room;
import Rooms.Slot;
import User.user;
public class Visitor extends user {
    private int totalReservedHours;
    public Visitor(){
        super(null,null,null,idStatic);
        totalReservedHours = 0;
    }
    public Visitor(String name, String password, String visitorType) {
        super(name,password,visitorType,idStatic);
        totalReservedHours = 0;
    }



    private static Scanner input = new Scanner(System.in);
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

    public void makeRes(ArrayList<Room> rooms) {
        System.out.println("How many rooms would you want?");
        int numOfRooms = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numOfRooms; i++) {
            try {
                LocalDate today = LocalDate.now();
                System.out.println("Today's date is" + today);
                LocalDate resDate = getDateInput("Enter The Date You Want (YYYY-MM-DD): ");
                System.out.println("-----------------------------------------------------------------");
                for(int j = 1; j <= rooms.size(); j++){
                    System.out.println("Room " + j);
                    rooms.get(j-1).displayAvailableSlots(resDate);
                    System.out.println("-----------------------------------------------------------------");
                }
                System.out.println("Enter room number you want to reserve: ");
                int roomNum = input.nextInt();
                input.nextLine();  // Consume newline
                LocalTime startTime = getTimeInput("Enter start time you want to reserve: ");
                LocalTime endTime = getTimeInput("Enter end time you want to reserve: ");

                int reservedHours = Duration.between(startTime, endTime).toHoursPart(); // Calculate hours
                totalReservedHours += reservedHours;
                rooms.get(roomNum-1).reserveSlot(startTime, this);
                // slot => reserved
                System.out.println("Reservation for room # " + roomNum + " and Slot # "+ startTime +" is successful.");
            } catch (Exception e) {
                System.out.println("Invalid input! Let's try again.");
                i--;
            }
            //start and end
            //function to reserve the room(time) return room number that is reserved
            //print You have reserved room number #
            reReserve(rooms);
        }
    }
    public void reReserve(ArrayList<Room> rooms){
        System.out.println("Do you want to make another reservation?\n 1. Make a new reservation\n 2. Return to Main Menu");
        int option = input.nextInt();
                switch (option){
            case 1:
                makeRes(rooms);
                break;
            case 2:
                options(rooms);
                break;
            default:
                System.out.println("Invalid input! Please try again.");
                reReserve(rooms);
        }
    }


    void cancelRes(ArrayList<Room> rooms){
        System.out.println("******NOTE: THEIR IS A CANCELLATION FEES******\n Do you want to continue (Y/N)");
        System.out.println("Fees Will Be 25%");
        String cont = input.nextLine();
        if (cont.equalsIgnoreCase("Y")){
            System.out.println("Enter Your Password To Cancel: ");
            String cancelPassword = input.nextLine();
            if (!cancelPassword.equals(super.getPassword())){
                System.out.println("Wrong Password!!! (try again)");
                cancelRes(rooms);
            }
            LocalDate cancelDate = getDateInput("Enter the Date you want to Cancel (YYYY-MM-DD): ");
            //pass user id to cancel func
            // System.out.println("Your Cancellation Fees = " Room.fees);
            //RoomAvailable.add(cancelDate);
            //RoomReserved.remove(cancelDate);
        } else if (cont.equalsIgnoreCase("N")) {
            options(rooms);
        }

    }
    void updateRes(ArrayList<Room> rooms){
        System.out.println("Enter Your Password To Update: ");
        String updatePassword = input.nextLine();
        if (!updatePassword.equals(super.getPassword())){
            System.out.println("wrong password!!!(try again)");
            updateRes(rooms);
        }
        System.out.println("Enter The Room Number you want to update: ");
        LocalTime startTime =getTimeInput("Enter start time you want to update: ");
        int numOfRoom = input.nextInt();

        //check the room is reserved (numOfRoom,startTime)
//        boolean found = Room.checkIfReserved(cancelDate, startTime);
//        if (!found) {
//            System.out.println("The specified room is not reserved at the given date and time.");
//            updateRes();
//        }
//
//            System.out.println("What Do You Want To Change? \n1.Room Date\n2.Room Time\n3.Return to Main Menu");
//            int choice = input.nextInt();
//            switch (choice) {
//                case 1:
//                    LocalDate newDate = getDateInput("Enter Your New Date");
//                    break;
//                case 2:
//                    LocalTime newTime= getTimeInput("Enter Your New Time");
//                    break;
//                case 3:
//                    options();
//                    break;
//            }
//            System.out.println("Displaying Available Rooms...");
//
//            //newDate OR newTime
//            //Room.
//            //Display
//            System.out.println("New Room is reserved And the old is cancelled.");
//            //remove from room Old one
//


    }


    public void signOut(ArrayList<user> users, ArrayList<Room> formals, ArrayList<Room> generals, ArrayList<Room> instructors) {
        user.startMenu(users, formals, generals, instructors);
    }
    public void options(ArrayList<Room>rooms) {
        while (true) {

            int option = input.nextInt();
            input.nextLine(); // Clear the buffer

            switch (option) {
                case 1:
                    makeRes(rooms);
                    break;
                case 2:
                    cancelRes(rooms);
                    break;
                case 3:
                    updateRes(rooms);
                    break;
                case 4:
                    System.out.println("Signing out...");
                    return; // Exit options menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public void sortVisitors(user currentUser, ArrayList<Formal> formals, ArrayList<General> generals, ArrayList<Instructor> instructors) {
        try {
            String visitorType = currentUser.getVisitorType();
            switch (visitorType) {
                case "Formal":
                    Formal formal = new Formal(currentUser.getName(), currentUser.getPassword(), visitorType);
                    formals.add(formal);
                    break;
                case "General":
                    General general = new General(currentUser.getName(), currentUser.getPassword(), visitorType);
                    generals.add(general);
                    break;
                case "Instructor":
                    Instructor instructor = new Instructor(currentUser.getName(), currentUser.getPassword(), visitorType);
                    instructors.add(instructor);
                    break;
                default:
                    System.out.println("Invalid visitor type: " + visitorType);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error sorting visitors: " + e.getMessage());
        }
    }

}
