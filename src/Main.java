import Admin_package.Admin;
import Rooms.GeneralRoom;
import Rooms.Room;
import User.user; // Renamed from `user`
import Visitors.Formal;
import Visitors.General;
import Visitors.Instructor;
import Visitors.Visitor;
import Rooms.Slot;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<user> users = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Slot> slots = new ArrayList<>();
        user.startMenu(users, rooms, slots);

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
                visitor.signOut(users, rooms, slots);
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
