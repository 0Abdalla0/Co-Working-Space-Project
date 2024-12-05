package Rooms;

public class TeachingRoom extends Room{
    public TeachingRoom(String name, int ID){
        super(name, ID);
        this.number_of_visitors = 0;
    }

    public void addVisitor(String visitor){
        if(number_of_visitors < 10){
//            this.visitors.add(visitor);
            this.number_of_visitors++;
        } else{
            System.out.println("Maximum visitors reached for TeachingRooom.");
        }
    }
}
