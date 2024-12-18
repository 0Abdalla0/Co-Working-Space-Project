import Rooms.*;
import User.user;
import Visitors.Instructor;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fnGeneralRoom = "fnGeneralRoom";
        String fnMeetingRoom ="fnMeetingRoom";
        String fnTeachingRoom ="fnTeachingRoom";
        String fnGenerals ="fnGenerals";
        String fnFormal ="fnFormal";
        String fnInstructor ="fnInstructor";
        filesHandler.readFromFile(fnGeneralRoom);
        filesHandler.readFromFile(fnMeetingRoom);
        filesHandler.readFromFile(fnTeachingRoom);

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

        //  slots
        Slot general1slot = new Slot(LocalDate.of(2024, 12, 19), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, general1);
        general1.getAvailableSlots().add(general1slot);
        Slot general2slot = new Slot(LocalDate.of(2024, 12, 19), LocalTime.of(12, 0), LocalTime.of(19, 0), 350, general1);
        general1.getAvailableSlots().add(general2slot);
        Slot meeting1slot = new Slot(LocalDate.of(2024, 12, 19), LocalTime.of(8, 0), LocalTime.of(10, 0), 350, meeting1);
        meeting1.getAvailableSlots().add(meeting1slot);
        Slot meeting2slot = new Slot(LocalDate.of(2024, 12, 19), LocalTime.of(11, 0), LocalTime.of(17, 0), 150, meeting1);
        meeting1.getAvailableSlots().add(meeting2slot);
        Slot teaching1Slot = new Slot(LocalDate.of(2024, 12, 19), LocalTime.of(9, 0), LocalTime.of(14, 0), 350, teaching1);
        teaching1.getAvailableSlots().add(teaching1Slot);
        Slot teaching2Slot = new Slot(LocalDate.of(2024, 12, 19), LocalTime.of(1, 0), LocalTime.of(9, 0), 350, teaching1);
        teaching1.getAvailableSlots().add(teaching2Slot);

        ArrayList<Instructor> instructors = new ArrayList<>();
        ArrayList<Visitor> visitors = new ArrayList<>();
        user.startMenu(visitors,meetingRooms, generalRooms,teachingRooms, instructors);

        while (true) {
            Visitor visitor = new Visitor();
            System.out.println("Do you want to Sign Out? (Y/N)");
            String signOutOption = input.next();
            if (signOutOption.equalsIgnoreCase("Y")) {
                System.out.println("You have signed out successfully.");
                visitor.signOut(visitors,meetingRooms, generalRooms,teachingRooms,instructors);
            } else if (signOutOption.equalsIgnoreCase("N")) {
                System.out.println("Going back to main menu...");
            } else {
                System.out.println("Invalid input. Please try again.");
            }
            filesHandler.saveToFile(fnGeneralRoom,generalRooms);
            filesHandler.saveToFile(fnMeetingRoom,meetingRooms);
            filesHandler.saveToFile(fnTeachingRoom,teachingRooms);
        }

    }
}