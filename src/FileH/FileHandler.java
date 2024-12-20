package FileH;

import Rooms.*;
import Visitors.Formal;
import Visitors.General;
import Visitors.Instructor;
import Visitors.Visitor;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;


public class FileHandler {

    // Method to write data to a file
    public void writeToFile(String fileName, String data) {
        BufferedWriter writer = null;
        try {
            File file = new File(fileName);
            // Ensure the directory exists
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            writer = new BufferedWriter(new FileWriter(file, true)); // 'true' appends data to file
            writer.write(data);
            writer.newLine(); // Add newline after the data
//            System.out.println("Data written to " + fileName + " successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // Close writer
                }
            } catch (IOException e) {
                System.out.println("Error closing the file writer: " + e.getMessage());
            }
        }
    }


    public void saveVisitorToFile(String fileName, Visitor visitor) {
        // Convert visitor information into a string format
        String data = "ID: " + visitor.getId() + ", Name: " + visitor.getName() +
                ", Password: " + visitor.getPassword() + ", Visitor type: " + visitor.getVisitorType();
        writeToFile(fileName, data); // Write the data to the file
    }

    // Method to save rooms (general, meeting, teaching) to a file
    public void saveRoomsToFile(String fileName, ArrayList<Room> rooms) {
        StringBuilder data = new StringBuilder();
        for (Room room : rooms) {
            data.append(room.toString()).append("\n"); // Append room data
        }
        writeToFile(fileName, data.toString()); // Save all the rooms to the file
    }

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = null;

        try {
            File file = new File(fileName);
            if (file.exists()) {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line); // Add each line to the list
                }
            } else {
                System.out.println("Error: File does not exist at " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // Close the reader
                }
            } catch (IOException e) {
                System.out.println("Error closing the file reader: " + e.getMessage());
            }
        }
        return lines; // Return the list of lines read from the file
    }

    // Method to read visitors from file and return them as a list of Visitor objects
    public ArrayList<Visitor> readVisitorsFromFile(String fileName) {
        ArrayList<Visitor> visitors = new ArrayList<>();
        ArrayList<String> lines = readFromFile(fileName); // Call the generic read method

        for (String line : lines) {
            String[] data = line.split(", ");
            int id = Integer.parseInt(data[0].split(": ")[1]);
            String name = data[1].split(": ")[1];
            String password = data[2].split(": ")[1];
            String visitorType = data[3].split(": ")[1];

            // Based on visitor type, create the appropriate Visitor object
            Visitor visitor = null;
            switch (visitorType) {
                case "Formal":
                    visitor = new Formal(name, password, visitorType);
                    break;
                case "General":
                    visitor = new General(name, password, visitorType);
                    break;
                case "Instructor":
                    visitor = new Instructor(name, password, visitorType);
                    break;
            }
            visitors.add(visitor); // Add the visitor object to the list
        }
        return visitors; // Return the list of visitors
    }

    // Method to read rooms from file and categorize them into meeting, general, and teaching rooms
    public void readRoomsFromFile(String fileName, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms) {
        ArrayList<String> lines = readFromFile(fileName); // Read lines from the rooms file

        for (String line : lines) {
            String[] parts = line.split(" ");  // Split line into parts to extract ID and Name
            int id = Integer.parseInt(parts[1]);  // ID of the room
            String name = parts[3];  // Name of the room (e.g., "general1", "meeting2", etc.)

            // Determine room type and create appropriate room object
            Room room = null;
            if (name.toLowerCase().startsWith("general")) {
                room = new GeneralRoom(name, id);
                generalRooms.add(room);  // Add to generalRooms list
            } else if (name.toLowerCase().startsWith("meeting")) {
                room = new MeetingRoom(name, id);
                meetingRooms.add(room);  // Add to meetingRooms list
            } else if (name.toLowerCase().startsWith("teaching")) {
                room = new TeachingRoom(name, id);
                teachingRooms.add(room);  // Add to teachingRooms list
            }
        }
    }

    // Method to save a single available slot of a room to a file
    public void saveAvailableSlotToFile(Slot slot) {
        StringBuilder data = new StringBuilder();
        // Append slot details (Date, Start Time, End Time, Fee)
        data.append("Date: ").append(slot.getDate())
                .append(" Start Time: ").append(slot.getStartTime())
                .append(" End Time: ").append(slot.getEndTime())
                .append(" Fee: ").append(slot.getFees())
                .append("\n");

        // File path based on the room's name and the available slots
        String fileName = "data/" + slot.getRoom().getName() + "_available_slots.txt";
        writeToFile(fileName, data.toString());  // Write to the file
    }

    // Method to save a single reserved slot of a room to a file
    public void saveReservedSlotToFile(Slot slot) {
        StringBuilder data = new StringBuilder();
        // Append slot details (Date, Start Time, End Time, Fee, Reserved by Visitor ID)
        data.append("Date: ").append(slot.getDate())
                .append(" Start Time: ").append(slot.getStartTime())
                .append(" End Time: ").append(slot.getEndTime())
                .append(" Fee: ").append(slot.getFees())
                .append(" Reserved by Visitor ID: ").append(slot.getUserID())
                .append("\n");

        // File path based on the room's name and the reserved slots
        String fileName = "data/" + slot.getRoom().getName() + "_reserved_slots.txt";
        writeToFile(fileName, data.toString());  // Write to the file
    }


    // Method to read a single available slot from a file and return it
    public Slot readAvailableSlotFromFile(Room room, String line) {
        try {
            // Skip lines that contain invalid data or headings like "Time:"
            if (line.contains("Time:") || line.trim().isEmpty()) {
                return null; // or skip this line
            }

            // Split the line into parts
            String[] parts = line.split(" ");  // Adjust this to match your file format

            // Ensure that the parts array contains enough elements (check for ArrayIndexOutOfBoundsException)
            if (parts.length < 4) {
                System.out.println("Skipping invalid line (not enough data): " + line);
                return null;  // Skip the line if it's invalid
            }

            // Extract the parts
            String dateString = parts[0];  // Expected: "2024-12-20"
            String startTimeString = parts[1];  // Expected: "10:00"
            String endTimeString = parts[2];  // Expected: "12:00"
            double fee = Double.parseDouble(parts[3]);  // Expected: "100.0"

            // Parse the date and time fields
            LocalDate date = LocalDate.parse(dateString);
            LocalTime startTime = LocalTime.parse(startTimeString);
            LocalTime endTime = LocalTime.parse(endTimeString);

            // Return a new Slot object
            return new Slot(date, startTime, endTime, fee, room);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in line: " + line);
            e.printStackTrace();
            return null;  // Skip this line and continue
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Missing data in line: " + line);
            e.printStackTrace();
            return null;  // Skip this line and continue
        } catch (Exception e) {
            System.out.println("Error occurred while parsing slot: " + line);
            e.printStackTrace();
            return null;  // Skip this line and continue
        }
    }





    // Method to read a single reserved slot from a file and return it
    public Slot readReservedSlotFromFile(Room room, String line) {
        // Split the line into parts
        String[] parts = line.split(" ");

        // Parse the slot details from the line
        LocalDate date = LocalDate.parse(parts[1]);
        LocalTime startTime = LocalTime.parse(parts[3]);
        LocalTime endTime = LocalTime.parse(parts[5]);
        double fee = Double.parseDouble(parts[7]);
        int visitorID = Integer.parseInt(parts[9]);

        // Create a new Slot object and set the user ID (visitor ID)
        Slot slot = new Slot(date, startTime, endTime, fee, room);
        slot.setUserID(visitorID);

        return slot;
    }



//    public void saveRoomSlotsToFile(String fileName, ArrayList<Room> rooms) {
//        StringBuilder data = new StringBuilder();
//        for (Room room : rooms) {
//            data.append("ID: ").append(room.getID())
//                    .append(" Name: ").append(room.getName())
//                    .append(" Available Slots: ").append(String.join(",", room.getAvailableSlots().toString()))
//                    .append(" Reserved Slots: ").append(String.join(",", room.getReservedSlots().toString()))
//                    .append("\n");
//        }
//        writeToFile(fileName, data.toString());
//    }

}
