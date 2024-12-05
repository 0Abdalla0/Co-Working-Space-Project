package Visitors;
import java.util.Scanner;
import Rooms.Room;

abstract public class Visitor {
    private String name;
    private String password;
    private final int id;
    private String visitorType;

    public String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    int getId() {
        return id;
    }
    void setPassword(String password) {
        this.password = password;
    }
    void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }
    public String getVisitorType() {
        return visitorType;
    }

    public Visitor(String name, String password, int id, String visitorType) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.visitorType = visitorType;
    }
    void makeRes(){}
    void deleteRes(){}
    void updateRes(){}
    void reward (int TotalHours){}
    void displayAvailableSlots(Room room,int hours){}

    Scanner input = new Scanner(System.in);
    public void options() {
        boolean retry = false;
        int option = input.nextInt();
        do {
            switch (option) {
                case 1:
                    makeRes();
                case 2:
                    deleteRes();
                case 3:
                    updateRes();
            }
            try {
                System.out.println("Do You Want To Choose Another Service? (Y/N)");
                String choose = input.next();
                if (choose.equalsIgnoreCase("Y")) {
                    retry = true;
                } else if (choose.equalsIgnoreCase("N")) {
                    retry = false;
                }
            }
            catch (Exception e) {
                System.out.println("Invalid Option");
            }
        } while (retry);
    }
}
