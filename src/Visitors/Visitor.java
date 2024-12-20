package Visitors;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Rooms.Room;
import Rooms.Slot;
import User.user;
public class Visitor extends user {
    protected int totalReservedHours;
    protected int totalFreeHours;

    public int getTotalFreeHours() {
        return totalFreeHours;
    }

    public Visitor(){
        super(null,null,null,idStatic);
        totalReservedHours = 0;
        totalFreeHours = 0;
    }
    public Visitor(String name, String password, String visitorType) {
        super(name,password,visitorType,idStatic);
        totalReservedHours = 0;
        totalFreeHours = 0;
    }

    static Scanner input = new Scanner(System.in);
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
                input.nextLine();
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
                input.nextLine();
            }
        }
    }
    public void options(ArrayList<Room> teachingRooms, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms,ArrayList<Visitor> visitors, ArrayList<Instructor> instructors) {
        while (true) {
            int option = 0;
            try {
                System.out.println("Select an option:\n1. Make Reservation\n2. Cancel Reservation\n3. Update Reservation\n4. Sign Out");
                option = input.nextInt();
            }catch (Exception e) {
                input.nextLine();
            }
            ArrayList<Room> roomsToDisplay = new ArrayList<>();
            if (this.getVisitorType().equals("Instructor")) {
                roomsToDisplay = teachingRooms;
            } else if (this.getVisitorType().equals("General")) {
                roomsToDisplay = generalRooms;
            } else if (this.getVisitorType().equals("Formal")) {
                roomsToDisplay = meetingRooms;
            }

            switch (option) {
                case 1:  // Make Reservation
                    reservation(roomsToDisplay); // Call the reservation method with only rooms as parameter
                    break;
                case 2:  // Cancel Reservation
                    cancelRes(roomsToDisplay); // Call the cancel method with only rooms as parameter
                    break;
                case 3:  // Update Reservation
                    updateRes(roomsToDisplay); // Call the update method with only rooms as parameter
                    break;
                case 4:
                    System.out.println("Signing out...");
                    signOut(visitors, meetingRooms, generalRooms, teachingRooms, instructors);
                    break; // Exit options menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void reservation(ArrayList<Room> rooms) {
        try {
            LocalDate today = LocalDate.now();
            System.out.println("Today's date is " + today);
            LocalDate resDate = getDateInput("Enter The Date You Want (YYYY-MM-DD): ");
            System.out.println("-----------------------------------------------------------------");
            for (int i = 0; i < rooms.size(); i++) {
                System.out.println("Room " + (i + 1) + ": " + rooms.get(i).getName());
                rooms.get(i).displayAvailableSlots(resDate);
            }
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Enter room number you want to reserve: ");
            int roomNum = input.nextInt();
            input.nextLine();
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
            // Ask the user for the cancellation date
            LocalDate cancelDate = getDateInput("Enter the Date you want to Cancel (YYYY-MM-DD): ");

            // Ask the user for the cancellation time
            LocalTime cancelTime = getTimeInput("Enter the time you want to Cancel (HH:MM): ");

            // Print the current size of Reserved Slots (for debugging purposes)
            System.out.println("Total Rooms: " + rooms.size());

            // Loop through each room in the rooms array
            for (Room room : rooms) {
                // Loop through each reserved slot in the room to find the matching reservation
                for (Slot slot : room.getReservedSlots()) {
                    // Check if the date and time match the user's input
                    if (slot.getDate().equals(cancelDate) && slot.getStartTime().equals(cancelTime)) {
                        // Check if the user is trying to cancel their own reservation
                        if (slot.getUserID() != this.getId()) {
                            System.out.println("This is not your reservation.");
                            return;  // Exit if it's not the user's reservation
                        }

                        // Calculate the cancellation fee (25% of the original fee)
                        double cancelFees = slot.getFees() * (25.0 / 100);
                        System.out.println("You are being fined: " + cancelFees);

                        // Remove the slot from ReservedSlots in the current room and add it to the available slots
                        room.getReservedSlots().remove(slot);

                        // Now, we need to check each room's available slots to find one that doesn't conflict with the canceled slot
                        boolean addedToAvailableSlot = false;

                        for (Room availableRoom : rooms) {
                            // Loop through the available slots of the room to check for conflict
                            for (Slot availableSlot : availableRoom.getAvailableSlots()) {
                                // Check if the available slot's date and time do not conflict with the canceled slot
                                if (!availableSlot.getDate().equals(cancelDate) || !availableSlot.getStartTime().equals(cancelTime)) {
                                    // No conflict found, add the canceled slot to this available slot's room
                                    availableRoom.getAvailableSlots().add(slot);
                                    addedToAvailableSlot = true;
                                    System.out.println("The slot has been successfully added to an available room.");
                                    break;
                                }
                            }
                            if (addedToAvailableSlot) {
                                break;
                            }
                        }

                        if (!addedToAvailableSlot) {
                            System.out.println("Could not find a room with no conflict to add the canceled slot.");
                        }

                        return;  // Exit after cancellation
                    }
                }
            }
        }
    }
    void updateRes(ArrayList<Room> rooms){
        System.out.println("Enter Your Password To Update: ");
        String updatePassword = input.nextLine();
        if (!updatePassword.equals(super.getPassword())){
            System.out.println("wrong password!!!(try again)");
            updateRes(rooms);
        }
        // Ask for the reservation details the user wants to update
        LocalDate updateDate = getDateInput("Enter the date of the reservation you want to update (YYYY-MM-DD): ");
        LocalTime updateTime = getTimeInput("Enter the start time of the reservation you want to update (HH:MM): ");

        boolean reservationFound = false;
        Slot slotToUpdate = null;

        // Loop through each room
        for (Room room : rooms) {
            // Loop through each reserved slot in the room
            for (Slot slot : room.getReservedSlots()) {
                // If the slot's date and time match the user's input, proceed
                if (slot.getDate().equals(updateDate) && slot.getStartTime().equals(updateTime)) {
                    // If the user is not the one who made the reservation
                    if (slot.getUserID() != this.getId()) {
                        System.out.println("This is not your reservation.");
                        return;  // Exit the function if it's not the user's reservation
                    }

                    // Reservation found, store the slot
                    slotToUpdate = slot;
                    reservationFound = true;
                    break;
                }
            }
            if (reservationFound) break;  // Exit the outer loop if reservation found
        }

        if (!reservationFound) {
            System.out.println("No reservation found for the given date and time.");
            return;
        }

        // Ask the user which part of the reservation they want to change
        System.out.println("What would you like to change?");
        System.out.println("1. Change the Start Time");
        System.out.println("2. Change the End Time");
        System.out.println("3. Return to Main Menu");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                // Change the start time
                LocalTime newStartTime = getTimeInput("Enter the new start time for the reservation (HH:MM): ");
                // Check if the new start time conflicts with any existing reservation in the room
                if (checkTimeConflict(slotToUpdate.getRoom(), newStartTime, slotToUpdate.getEndTime())) {
                    System.out.println("The new start time conflicts with an existing reservation.");
                    System.out.println("Would you like to cancel your current reservation and reserve a new slot?");
                    System.out.println("1. Yes, cancel the reservation and re-reserve");
                    System.out.println("2. No, go back to main menu");
                    int cancelChoice = input.nextInt();

                    if (cancelChoice == 1) {
                        // Cancel the current reservation and remove the slot from the room's reserved slots
                        if (slotToUpdate.getRoom().getReservedSlots().contains(slotToUpdate)) {
                            slotToUpdate.getRoom().getReservedSlots().remove(slotToUpdate); // Remove the slot from reserved slots
                            slotToUpdate.getRoom().getAvailableSlots().add(slotToUpdate);   // Add the slot to available slots
                            System.out.println("The reservation has been canceled.");
                        } else {
                            System.out.println("Slot not found in the reserved slots.");
                        }

                        // Call the reservation function to let the user choose a new slot
                        reservation(rooms);
                    } else {
                        System.out.println("Returning to the main menu...");
                        return;
                    }
                } else {
                    // Update the slot's start time
                    slotToUpdate.setStartTime(newStartTime);
                    System.out.println("The reservation start time has been updated.");
                }
                break;

            case 2:
                // Change the end time
                LocalTime newEndTime = getTimeInput("Enter the new end time for the reservation (HH:MM): ");
                // Check if the new end time conflicts with any existing reservation in the room
                if (checkTimeConflict(slotToUpdate.getRoom(), slotToUpdate.getStartTime(), newEndTime)) {
                    System.out.println("The new end time conflicts with an existing reservation.");
                    System.out.println("Would you like to cancel your current reservation and reserve a new slot?");
                    System.out.println("1. Yes, cancel the reservation and re-reserve");
                    System.out.println("2. No, go back to main menu");
                    int cancelChoice = input.nextInt();

                    if (cancelChoice == 1) {
                        // Cancel the old reservation (remove the old slot from reserved slots)
                        if (slotToUpdate.getRoom().getReservedSlots().contains(slotToUpdate)) {
                            slotToUpdate.getRoom().getReservedSlots().remove(slotToUpdate); // Remove the slot from reserved slots
                            slotToUpdate.getRoom().getAvailableSlots().add(slotToUpdate);   // Add the slot to available slots
                            System.out.println("The reservation has been canceled.");
                        } else {
                            System.out.println("Slot not found in the reserved slots.");
                        }

                        // Call the reservation function to let the user choose a new slot
                        reservation(rooms);
                    } else {
                        System.out.println("Returning to the main menu...");
                        return;
                    }
                } else {
                    // Update the slot's end time
                    slotToUpdate.setEndTime(newEndTime);
                    System.out.println("The reservation end time has been updated.");
                }
                break;

            case 3:
                // Return to main menu
                System.out.println("Returning to the main menu...");
                return;

            default:
                System.out.println("Invalid choice. Returning to the main menu.");
        }
    }

    boolean checkTimeConflict(Room room, LocalTime newStartTime, LocalTime newEndTime) {
        for (Slot slot : room.getReservedSlots()) {
            if (newStartTime.isBefore(slot.getEndTime()) && newEndTime.isAfter(slot.getStartTime())) {
                return true;  // Conflict found
            }
        }
        return false;  // No conflict
    }

    public void signOut(ArrayList<Visitor> visitors, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        user.startMenu(visitors, meetingRooms, generalRooms, teachingRooms, instructors);
    }
    public Visitor leaderBoard(ArrayList<Visitor> visitors) {
        int highestScore = 0;
        Visitor winner = null;
        for (Visitor visitor : visitors) {
            if (visitor.totalReservedHours>highestScore) {
                highestScore = visitor.totalReservedHours;
                winner = visitor;
            }
        }
        return winner;
    }
    public Visitor rewardSys(){
        return null;
    }

}
