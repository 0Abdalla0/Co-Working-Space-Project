package Visitors;

import Rooms.GeneralRoom;
import Rooms.Slot;

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
    public void options() {
        System.out.println("Welcome **"+ super.getName() +"** To The General Section\n Choose An Option:\n 1. Make A Reservation\n 2. Delete A Reservation\n 3. Update A Reservation\n 4. Sign out");
//        super.options();
    }
}
