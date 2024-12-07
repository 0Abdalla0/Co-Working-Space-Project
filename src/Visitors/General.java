package Visitors;

import Rooms.GeneralRoom;
import Rooms.Slot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class General extends Visitor {
    private int hours;
    public General(String name, String password, int id, String visitorType) {
        super(name, password, id, visitorType);
        this.hours = 0;
    }

    Scanner input = new Scanner(System.in);
    Date date =new Date();
    ArrayList<GeneralRoom> generals=new ArrayList<>();
    ArrayList<Slot> slots=new ArrayList<>();


    public void options() {
        System.out.println("Welcome To The General Section\n Choose An Option:\n 1. Make A Reservation\n 2. Delete A Reservation\n 3. Update A Reservation");
    }
}
