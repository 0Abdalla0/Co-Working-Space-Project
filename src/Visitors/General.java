package Visitors;
import Rooms.MeetingRoom;
import Rooms.GeneralRoom;
import Rooms.Room;
import Rooms.Slot;
import User.user;
import Rooms.Room;
import Rooms.TeachingRoom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class General extends Visitor {
    private int hours;
    public General(String name, String password, String visitorType) {
        super(name, password, visitorType);
        this.hours = 0;
    }
    Scanner input = new Scanner(System.in);


//    @Override
    public void options(ArrayList<Room>rooms,ArrayList<Slot> ReservedSlots,ArrayList<Slot> Availableslots,ArrayList<user> users, ArrayList<Room> meetingRooms, ArrayList<Room> generalRooms, ArrayList<Room> teachingRooms, ArrayList<Instructor> instructors) {
        System.out.println("Welcome **"+ super.getName() +"** To The General Section");
//       super.options(rooms,ReservedSlots,Availableslots,users,meetingRooms,generalRooms,teachingRooms,instructors);
    }
}
