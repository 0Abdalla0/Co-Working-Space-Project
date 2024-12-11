import Admin_package.Admin;
import Rooms.*;
import User.user; // Renamed from `user`
import Visitors.Formal;
import Visitors.General;
import Visitors.Instructor;
import Visitors.Visitor;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
        GeneralRoom general1 = new GeneralRoom("general1",1);
        rooms.add(general1);
        GeneralRoom general2 = new GeneralRoom("general2",2);
        rooms.add(general2);
        MeetingRoom meeting1 = new MeetingRoom("meeting1",3);
        rooms.add(meeting1);
        MeetingRoom meeting2 = new MeetingRoom("meeting2",4);
        rooms.add(meeting2);
        MeetingRoom meeting3 = new MeetingRoom("meeting3",5);
        rooms.add(meeting3);
        TeachingRoom teaching1 = new TeachingRoom("teaching1",6);
        rooms.add(teaching1);
        TeachingRoom teaching2 = new TeachingRoom("teaching2",7);
        rooms.add(teaching2);

        ArrayList<user> users = new ArrayList<>();
        ArrayList<Slot> slots = new ArrayList<>();
        user.startMenu(users, rooms, slots,general1);

        ArrayList<Formal> formals = new ArrayList<>();
        ArrayList<General> generals = new ArrayList<>();
        ArrayList<Instructor> instructors = new ArrayList<>();
        ArrayList<Visitor> visitors = new ArrayList<>();
        // Debugging: Print out registered users
        while (true) {
            System.out.println("Total registered users: " + users.size());
            Visitor visitor = new Visitor();
            // Ensure visitors list is populated before iteration
            for (user user : users) {
                visitor.sortVisitors(user, formals, generals, instructors);
                visitors.addAll(formals);
                visitors.addAll(generals);
                visitors.addAll(instructors);
            }
            for (Visitor visitor1 : visitors) {
                visitor1.options();
            }
            System.out.println("Formal visitors: " + formals.size());
            System.out.println("General visitors: " + generals.size());
            System.out.println("Instructor visitors: " + instructors.size());
            // Sign-out option
            System.out.println("Do you want to Sign Out? (Y/N)");
            String signOutOption = input.next();
            if (signOutOption.equalsIgnoreCase("Y")) {
                System.out.println("You have signed out successfully.");
                visitor.signOut(users, rooms, slots,general1);
            } else if (signOutOption.equalsIgnoreCase("N")) {
                System.out.println("Going back to main menu...");
                for (Visitor visitor1 : visitors) {
                    visitor1.options();
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
            // Output visitor counts
        }

    }
}
