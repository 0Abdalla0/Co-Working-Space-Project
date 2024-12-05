package Visitors;
import Rooms.MeetingRoom;
import Rooms.Slot;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Formal extends Visitor {
    private int hours;
    public Formal(String name, String password, int id, String visitorType) {
        super(name, password, id, visitorType);
        this.hours = 0;
    }

    Scanner input = new Scanner(System.in);
    Date date =new Date();
    ArrayList<MeetingRoom> meetings=new ArrayList<>();
    ArrayList<Slot> slots=new ArrayList<>();


    //waiting for finding available slots
    public  void makeRes(){

    }
    public void displayAvailableSlots(int numOfRooms,int hours){

    }
    public void deleteRes(){

    };
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
    public void options() {
        System.out.println("Welcome To The Meetings Section\n Choose An Option:\n 1. Make A Reservation\n 2. Delete A Reservation\n 3. Update A Reservation");
    }
}
