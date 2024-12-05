import Admin_package.Admin;
import Rooms.MeetingRoom;
import Rooms.Room;

public class Main {
    public static void main(String[] args) {
    Room Meeting = new MeetingRoom("meeting1" , 15);
    Admin ahmed  = new Admin("admin", "admin");
    ahmed.addSlots(Meeting);
    }
}