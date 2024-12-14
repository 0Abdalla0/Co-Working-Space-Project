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
    public void reservation(ArrayList<Room> rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots) {
        try {
            LocalDate today = LocalDate.now();
            System.out.println("Today's date is " + today);
            LocalDate resDate = getDateInput("Enter The Date You Want (YYYY-MM-DD): ");
            System.out.println("-----------------------------------------------------------------");
            for (Room room : rooms) {
                room.displayAvailableSlots(resDate);
            }
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Enter room number you want to reserve: ");
            int roomNum = input.nextInt();
            LocalTime startTime = getTimeInput("Enter start time you want to reserve: ");
            LocalTime endTime = getTimeInput("Enter end time you want to reserve: ");

            int reservedHours = Duration.between(startTime, endTime).toHoursPart(); // Calculate hours
            totalReservedHours += reservedHours;
            rooms.get(roomNum-1).reserveSlot(startTime, this);
            System.out.println("Reservation for room # " + roomNum + " and Slot # "+ startTime +" is successful.");
        } catch (Exception e) {
            System.out.println("Invalid input! Let's try again.");
        }
    }
    public void makeRes(ArrayList<Room> rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots) {
        System.out.println("How many rooms would you want?");
        int numOfRooms = input.nextInt();
//        input.nextLine();

        for (int i = 0; i < numOfRooms; i++) {
           try {
               reservation(rooms, ReservedSlots, Availableslots);
           }catch (Exception e){
               i--;
           }

            reReserve(rooms, ReservedSlots, Availableslots);
        }
    }
    public void reReserve(ArrayList<Room> rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots){
        System.out.println("Do you want to make another reservation?\n 1. Make a new reservation\n 2. Return to Main Menu");
        int option = input.nextInt();
                switch (option){
            case 1:
                makeRes(rooms,ReservedSlots,Availableslots);
                break;
            case 2:
                options(rooms,ReservedSlots,Availableslots);
                break;
            default:
                System.out.println("Invalid input! Please try again.");
                reReserve(rooms,ReservedSlots,Availableslots);
        }
    }


    void cancelRes(ArrayList<Room> rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots){
        System.out.println("******NOTE: THEIR IS A CANCELLATION FEES******\n Do you want to continue (Y/N)");
        System.out.println("Fees Will Be 25%");
        String cont = input.nextLine();
        if (cont.equalsIgnoreCase("Y")){
            System.out.println("Enter Your Password To Cancel: ");
            String cancelPassword = input.nextLine();
            if (!cancelPassword.equals(super.getPassword())){
                System.out.println("Wrong Password!!! (try again)");
                cancelRes(rooms, ReservedSlots, Availableslots);
            }
            LocalDate cancelDate = getDateInput("Enter the Date you want to Cancel (YYYY-MM-DD): ");
                System.out.println(ReservedSlots.size());

            for (Slot slot : ReservedSlots) {
                if (slot.getDate().equals(cancelDate)){
                    if (slot.getUserID() != this.getId()){
                        System.out.println("Not Your Slot");
                    }
                    double cancelFees = slot.getFees()*((double) 25 /100);
                    System.out.println("Next time you will get fined: "+cancelFees);
                    ReservedSlots.remove(slot);
                    Availableslots.add(slot);
                }
            }
        } else if (cont.equalsIgnoreCase("N")) {
            options(rooms,ReservedSlots,Availableslots);
        }

    }
    void updateRes(ArrayList<Room> rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots){
        System.out.println("Enter Your Password To Update: ");
        String updatePassword = input.nextLine();
        if (!updatePassword.equals(super.getPassword())){
            System.out.println("wrong password!!!(try again)");
            updateRes(rooms,ReservedSlots,Availableslots);
        }
        LocalTime startTime =getTimeInput("Enter start time you want to update: ");
        int numOfRoom = input.nextInt();

        //check the room is reserved (numOfRoom,startTime)

        for (Slot slot : ReservedSlots) {

        boolean found = slot.isReserved();
        if (!found) {
            System.out.println("The specified room is not reserved at the given date and time.");
            updateRes(rooms, ReservedSlots,Availableslots);
        }

            System.out.println("What Do You Want To Change? \n1.Room Date & Time\n2.Return to Main Menu");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    reservation(rooms, ReservedSlots, Availableslots);
                    break;
                case 2:
                    options(rooms,ReservedSlots,Availableslots);
                    break;
            }
            System.out.println("New Room is reserved And the old is cancelled.");
        }
    }


    public void signOut(ArrayList<user> users, ArrayList<Room> formals, ArrayList<Room> generals, ArrayList<Room> instructors) {
        user.startMenu(users, formals, generals, instructors);
    }
    public void options(ArrayList<Room>rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots) {
        while (true) {
            int option = input.nextInt();
            input.nextLine(); // Clear the buffer

            switch (option) {
                case 1:
                    makeRes(rooms,ReservedSlots,Availableslots);
                    break;
                case 2:
                    cancelRes(rooms, ReservedSlots, Availableslots);
                    break;
                case 3:
                    updateRes(rooms,ReservedSlots,Availableslots);
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
