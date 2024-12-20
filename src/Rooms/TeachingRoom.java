package Rooms;

public class TeachingRoom extends Room{
    private static double totalFees;
    public TeachingRoom(String name, int ID){
        super(name, ID);
        this.number_of_visitors = 0;
    }

    public void addFees() {
        totalFees += this.calculateTotalFees(); // Adds this room's fees to the total
    }

    // Static method to get the total fees across all Meeting rooms
    public static double getTotalFees() {
        return totalFees;
    }
}
