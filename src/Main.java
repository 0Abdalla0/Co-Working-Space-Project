import Admin_package.Admin;
import Rooms.MeetingRooms;
import Rooms.Room;

public class Main {
    public static void main(String[] args) {
    Room Meeting = new MeetingRooms("meeting1" , 15);
    Admin ahmed  = new Admin("admin", "admin");
    ahmed.addSlots(Meeting);
    }
}