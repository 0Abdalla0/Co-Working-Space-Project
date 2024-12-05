package Rooms;

public class GeneralRooms extends Room{
    public GeneralRooms(String name, int ID) {
        super(name, ID);
        // Setting max number of visitors for GeneralRoom as 20
        this.number_of_visitors = 0;
    }


//    public void addVisitor(String visitor){
//        if (this.number_of_visitors < 20) {
////            this.visitors.add(visitor);
//            this.number_of_visitors++;
//        } else {
//            System.out.println("Maximum visitors reached for General Room.");
//        }
//    }
}