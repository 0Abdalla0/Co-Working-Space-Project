package Visitors;
import Rooms.MeetingRoom;
import Rooms.Room;
import Rooms.Slot;
import User.user;
import Rooms.GeneralRoom;
import Rooms.Room;
import Rooms.TeachingRoom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Formal extends Visitor {
    private int hours;
    public Formal(String name, String password, String visitorType) {
        super(name, password, visitorType);
        this.hours = 0;
    }


    Scanner input = new Scanner(System.in);
    Date date =new Date();
    ArrayList<MeetingRoom> meetings=new ArrayList<>();
    ArrayList<Slot> slots=new ArrayList<>();

    public void updateRes(){
        System.out.println("What Do You Want To Update?\n 1. Time Of Meeting\n 2. Date Of Meeting\n3. Update A Reservation");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("When Is The New Meeting?");
        }
    };
    public void reward (int TotalNumOfHours){
    };
    public void options(ArrayList<Room>rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots,ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        System.out.println("Welcome **"+super.getName()+"** To The Meetings Section");
        super.options(rooms,ReservedSlots,Availableslots,users,meetingRooms,generalRooms,teachingRooms,instructors);
    }
}
