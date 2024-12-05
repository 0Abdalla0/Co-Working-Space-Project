package Rooms;

public class MeetingRoom extends Room {

    public MeetingRoom(String name, int ID) {
        super(name, ID);
        this.number_of_visitors = 0;
    }

//    @Override
//    public void addVisitor(String visitor) {
//        if (number_of_visitors < 10) {
//        this.visitors.add(visitor);
//            number_of_visitors++;
//        } else {
//            System.out.println("Maximum visitors reached for Meeting Room.");
//        }
//    }
}