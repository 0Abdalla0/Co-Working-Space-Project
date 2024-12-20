package Rooms;
import FileH.FileHandler;
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
    Scanner input = new Scanner(System.in);
    public  LocalTime getTimeInput(String prompt) {
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
    public  LocalDate getDateInput(String prompt) {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    @Override
    public String toString() {
        return "ID: " + ID + " Name: " + name;
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
        Slot newSlot = new Slot(date, startTime, endTime, fee, this);
        Availableslots.add(newSlot);

        // After adding the slot, save it to the file
        FileHandler fileHandler = new FileHandler();  // Assuming you have a FileHandler class with file-writing methods
        fileHandler.saveAvailableSlotToFile(newSlot);  // Save the new slot to file

        // Provide feedback to the user
        System.out.println("Slot added successfully");
    }

    // check date to display its slots
    public void displayAvailableSlots(LocalDate date) {
        boolean foundSlots = false;

        System.out.println("Available slots for " + date + ":");

        for (int i = 0; i < Availableslots.size(); i++) {
            Slot slot = Availableslots.get(i);

            if (slot.getDate().equals(date)) {
                System.out.println("Slot " + (i + 1));
                System.out.println(slot.getStartTime() + " - " + slot.getEndTime());
                foundSlots = true;
            }
        }

        if (!foundSlots) {
            System.out.println("No available slots for this date.");
        }
    }
    public void displayAllAvailableSlots() {
        boolean foundSlots = false;

        System.out.println("Displaying all available slots:");


        for (int i = 0; i < Availableslots.size(); i++) {
            Slot slot = Availableslots.get(i);


            LocalDate slotDate = slot.getDate();
            String roomName = getName();

            System.out.println("Date: " + slotDate);
            System.out.println("Slot " + (i + 1));
            System.out.println(slot.getStartTime() + " - " + slot.getEndTime());
            System.out.println("-------------------------------");

            foundSlots = true;
        }

        if (!foundSlots) {
            System.out.println("No available slots found.");
        }
    }
    public void displayAllReservedSlots() {
        boolean foundSlots = false;
        System.out.println("Displaying all reserved slots:");


        for (int i = 0; i < ReservedSlots.size(); i++) {
            Slot slot = ReservedSlots.get(i);
            LocalDate slotDate = slot.getDate();
            String roomName = getName();

            System.out.println("Date: " + slotDate);
            System.out.println("Slot " + (i + 1));
            System.out.println(slot.getStartTime() + " - " + slot.getEndTime());
            System.out.println("-------------------------------");

            foundSlots = true;
        }

        if (!foundSlots) {
            System.out.println("No reserved slots found.");
        }

    }


    // Method to check if there are available slots for specific date
    public boolean hasAvailableSlots(LocalDate date) {
        // Check if there are no reservations for the date
        for (Slot slot : getAvailableSlots()) {
            if (slot.getDate().equals(date)) {
                return true;  // If there is any reservation on the date, return false
            }
        }
        return false;  // No reservations for the date, so return true
    }

    // CHANGE FROM INDEX TO START TIME
    public void reserveSlot(LocalTime startTime, LocalTime endTime, Visitor visitor) {
        // Check if any slot is available
        if (Availableslots.isEmpty()) {
            System.out.println("Error: No available slots.");
            return;
        }

        Slot selectedSlot = null;

        // Check for a slot with matching start time and end time
        for (Slot slot : Availableslots) {
            if (slot.getStartTime().equals(startTime) && slot.getEndTime().equals(endTime)) {
                selectedSlot = slot;
                break;
            }
        }

        if (selectedSlot == null) {
            System.out.println("Error: No slot found for the given start and end time.");
            return;
        }

        // Confirmation
        System.out.println("Reserving the following slot:");
        System.out.println("Date: " + selectedSlot.getDate());
        System.out.println("Start Time: " + selectedSlot.getStartTime());
        System.out.println("End Time: " + selectedSlot.getEndTime());
        System.out.println("Fee: " + selectedSlot.getFees());

        // Reserve the slot
        selectedSlot.setUserID(visitor.getId());
        ReservedSlots.add(selectedSlot);
        Availableslots.remove(selectedSlot);
        visitors.add(visitor);

        FileHandler fileHandler = new FileHandler();
        fileHandler.saveReservedSlotToFile(selectedSlot);

        System.out.println("Slot reserved successfully.");
    }



    public double calculateTotalFees() {
        double totalFees = 0.0;

        for (Slot slot : ReservedSlots) {
            totalFees += slot.getFees();
        }

        return totalFees;
    }

    public ArrayList<Slot> getAvailableSlots() {
        return Availableslots;
    }

    public ArrayList<Slot> getReservedSlots() {
        return ReservedSlots;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public void updateSlot() {
        Scanner input = new Scanner(System.in);
        LocalDate date;
        LocalTime startTime;
        Slot targetSlot = null;

        // Input and validate date
        while (true) {
            try {
                date = getDateInput("Enter date of the slot to update (yyyy-MM-dd): ");
                break; // Valid date
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        // Input and validate start time
        while (true) {
            try {
                startTime = getTimeInput("Enter start time of the slot to update (HH:mm): ");

                // Find the slot by date and start time
                for (Slot slot : Availableslots) {
                    if (slot.getDate().equals(date) && slot.getStartTime().equals(startTime)) {
                        targetSlot = slot;
                        break;
                    }
                }

                if (targetSlot == null) {
                    System.out.println("Error: No slot found with the given date and start time.");
                    return;
                }

                break; // Slot found
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Please use HH:mm.");
            }
        }

        while (true) {
            try {
                // Display slot details
                System.out.println("Slot's Date: " + targetSlot.getDate());
                System.out.println("Slot's Start Time: " + targetSlot.getStartTime());
                System.out.println("Slot's End Time: " + targetSlot.getEndTime());
                System.out.println("Slot's Fees: " + targetSlot.getFees());

                // Menu for updating slot details
                System.out.println("What do you want to update?");
                System.out.println("1. Date");
                System.out.println("2. Start time");
                System.out.println("3. End time");
                System.out.println("4. Fees");
                System.out.println("5. Exit");

                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        // Update date
                        while (true) {
                            try {
                                LocalDate newDate = getDateInput("Enter new date (yyyy-MM-dd): ");
                                if (newDate.isBefore(LocalDate.now())) {
                                    System.out.println("Error: The date cannot be in the past.");
                                } else {
                                    targetSlot.setDate(newDate);
                                    System.out.println("Slot's date updated successfully.");
                                    break;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
                            }
                        }
                        break;

                    case 2:
                        // Update start time
                        while (true) {
                            try {
                                LocalTime newStartTime = getTimeInput("Enter new start time (HH:mm): ");

                                // Check for conflicts
                                boolean conflict = false;
                                for (Slot slot : Availableslots) {
                                    if (slot.getDate().equals(date) && slot.getStartTime().equals(newStartTime)) {
                                        conflict = true;
                                        break;
                                    }
                                }

                                if (conflict) {
                                    System.out.println("Error: A slot with the new start time already exists on the same date.");
                                } else {
                                    targetSlot.setStartTime(newStartTime);
                                    System.out.println("Slot start time updated successfully.");
                                    break;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Invalid time format. Please use HH:mm.");
                            }
                        }
                        break;

                    case 3:
                        // Update end time
                        while (true) {
                            try {
                                LocalTime newEndTime = getTimeInput("Enter new end time (HH:mm): ");
                                if (newEndTime.isBefore(targetSlot.getStartTime())) {
                                    System.out.println("Error: End time must be after the start time.");
                                } else {
                                    targetSlot.setEndTime(newEndTime);
                                    System.out.println("Slot end time updated successfully.");
                                    break;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Invalid time format. Please use HH:mm.");
                            }
                        }
                        break;

                    case 4:
                        // Update fees
                        while (true) {
                            try {
                                System.out.print("Enter new fees: ");
                                double newFees = Double.parseDouble(input.nextLine());
                                if (newFees < 50) {
                                    System.out.println("Error: Fee cannot be less than 50.");
                                } else {
                                    targetSlot.setFees(newFees);
                                    System.out.println("Slot fees updated successfully.");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Fees must be a valid number.");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Exiting slot update...");
                        return;

                    default:
                        System.out.println("Error: Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid integer.");
            }
        }
    }

    public abstract void addFees();


}