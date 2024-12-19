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
    public Formal(String name, String password, String visitorType) {
        super(name, password, visitorType);
        super.totalReservedHours = 0;
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
    }

public Visitor rewardSys() {
    if (this.totalReservedHours % 6 == 0) {
        this.totalFreeHours++;
        return this;
    }
    return null;
}

}
