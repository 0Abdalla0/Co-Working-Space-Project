package Visitors;
import Rooms.MeetingRoom;
import Rooms.GeneralRoom;
import User.user;
import Rooms.Room;
import Rooms.Slot;
import Rooms.TeachingRoom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;
public class Instructor extends Visitor {
    private int hours;
    public Instructor(String name, String password, String visitorType) {
        super(name, password, visitorType);
        this.hours = 0;
    }
    public String toString() {
        return "ID: " + getId() + ", Name: " + getName() + ", Password: " + getPassword();
    }

    Scanner input = new Scanner(System.in);
    Date date =new Date();
    ArrayList<TeachingRoom> teaching=new ArrayList<>();
    ArrayList<Slot> slots=new ArrayList<>();

    public void options(ArrayList<Room>rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots,ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        System.out.println("Welcome **"+super.getName()+"** To The Instructors Section");
//        super.options(rooms,ReservedSlots,Availableslots,users,meetingRooms,generalRooms,teachingRooms,instructors);
    }

}
