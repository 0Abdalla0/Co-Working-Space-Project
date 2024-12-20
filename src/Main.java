import FileH.FileHandler;
import Rooms.*;
import User.user;
import Visitors.Instructor;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.logging.FileH.FileHandler;

public class Main {
    public static void main(String[] args) {
        String fnGeneralRoom = "fnGeneralRoom";
        String fnMeetingRoom ="fnMeetingRoom";
        String fnTeachingRoom ="fnTeachingRoom";
        String fnGenerals ="fnGenerals";
        String fnFormal ="fnFormal";
        String fnInstructor ="fnInstructor";
//        filesHandler.readFromFile(fnGeneralRoom);
//        filesHandler.readFromFile(fnMeetingRoom);
//        filesHandler.readFromFile(fnTeachingRoom);

        // Create an object of FileH.FileHandler
        FileHandler fileHandler = new FileHandler();

        // Writing data to the file
//        fileHandler.writeToFile("wigo");
//        fileHandler.writeToFile("Another line of data.");

        // Reading data from the file
//        fileHandler.readFromFile();

        Scanner input = new Scanner(System.in);
        ArrayList<Room> meetingRooms = new ArrayList<>();
        ArrayList<Room> generalRooms = new ArrayList<>();
        ArrayList<Room> teachingRooms = new ArrayList<>();
        GeneralRoom general1 = new GeneralRoom("general1",1);
        generalRooms.add(general1);
        GeneralRoom general2 = new GeneralRoom("general2",2);
        generalRooms.add(general2);
        MeetingRoom meeting1 = new MeetingRoom("meeting1",3);
        meetingRooms.add(meeting1);
        MeetingRoom meeting2 = new MeetingRoom("meeting2",4);
        meetingRooms.add(meeting2);
        MeetingRoom meeting3 = new MeetingRoom("meeting3",5);
        meetingRooms.add(meeting3);
        TeachingRoom teaching1 = new TeachingRoom("teaching1",6);
        teachingRooms.add(teaching1);
        TeachingRoom teaching2 = new TeachingRoom("teaching2",7);
        teachingRooms.add(teaching2);
        TeachingRoom teaching3 = new TeachingRoom("teaching3",8);
        teachingRooms.add(teaching3);

        fileHandler.saveRoomsToFile("data/rooms.txt", meetingRooms);
        fileHandler.saveRoomsToFile("data/rooms.txt", generalRooms);
        fileHandler.saveRoomsToFile("data/rooms.txt", teachingRooms);

        //  slots
        Slot general1slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, general1);
        general1.getAvailableSlots().add(general1slot);
        Slot general2slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, general2);
        general2.getAvailableSlots().add(general2slot);
        Slot general6H = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(12, 0), LocalTime.of(19, 0), 350, general1);
        general1.getAvailableSlots().add(general6H);

        Slot meeting1slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, meeting1);
        meeting1.getAvailableSlots().add(meeting1slot);
        Slot meeting2slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, meeting2);
        meeting2.getAvailableSlots().add(meeting2slot);
        Slot meeting3slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, meeting1);
        meeting3.getAvailableSlots().add(meeting3slot);

        Slot teaching12H = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(21, 0), 350, teaching1);
        teaching1.getAvailableSlots().add(teaching12H);
        Slot teaching2slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, teaching2);
        teaching2.getAvailableSlots().add(teaching2slot);
        Slot teaching3slot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, teaching3);
        teaching2.getAvailableSlots().add(teaching3slot);

        Slot general1resslot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(19, 0), LocalTime.of(21, 0), 350, general1);
        general2.getReservedSlots().add(general1resslot);
        Slot meeting1resslot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(10, 0), LocalTime.of(12, 0), 350, meeting1);
        meeting2.getReservedSlots().add(meeting1resslot);
        Slot teaching1resSlot = new Slot(LocalDate.of(2024, 12, 23), LocalTime.of(10, 0), LocalTime.of(12, 0), 350, teaching1);
        teaching2.getReservedSlots().add(teaching1resSlot);

        ArrayList<Instructor> instructors = new ArrayList<>();
        ArrayList<Visitor> visitors = new ArrayList<>();
        user.startMenu(visitors,meetingRooms, generalRooms,teachingRooms, instructors, fileHandler);

        while (true) {
            Visitor visitor = new Visitor();
            System.out.println("Do you want to Sign Out? (Y/N)");
            String signOutOption = input.next();
            if (signOutOption.equalsIgnoreCase("Y")) {
                System.out.println("You have signed out successfully.");
                visitor.signOut(visitors,meetingRooms, generalRooms,teachingRooms,instructors, fileHandler);
            } else if (signOutOption.equalsIgnoreCase("N")) {
                System.out.println("Going back to main menu...");
            } else {
                System.out.println("Invalid input. Please try again.");
            }
//            filesHandler.saveToFile(fnGeneralRoom,generalRooms);
//            filesHandler.saveToFile(fnMeetingRoom,meetingRooms);
//            filesHandler.saveToFile(fnTeachingRoom,teachingRooms);
        }

    }
}