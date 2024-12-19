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

    public Visitor rewardSys(int hours){
        hours= super.totalReservedHours;
        if ( hours >= 12){
            return this;
        }
        return null;
    }

}
