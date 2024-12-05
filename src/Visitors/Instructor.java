package Visitors;

import Rooms.Slot;
import Rooms.TeachingRoom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Instructor extends Visitor {
    private int hours;
    public Instructor(String name, String password, int id, String visitorType) {
        super(name, password, id, visitorType);
        this.hours = 0;
    }

    Scanner input = new Scanner(System.in);
    Date date =new Date();
    ArrayList<TeachingRoom> teaching=new ArrayList<>();
    ArrayList<Slot> slots=new ArrayList<>();

    public void options() {
        System.out.println("Welcome To The Instructors Section\n Choose An Option:\n 1. Make A Reservation\n 2. Delete A Reservation\n 3. Update A Reservation");
    }

}
