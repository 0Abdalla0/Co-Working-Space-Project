package Rooms;

public class GeneralRoom extends Room{
    private static double totalFees;
    public GeneralRoom(String name, int ID) {
        super(name, ID);
        // Setting max number of visitors for GeneralRoom as 20
        this.number_of_visitors = 0;
    }

    public void addFees() {
        totalFees += this.calculateTotalFees(); // Adds this room's fees to the total
    }

    public static double getTotalFees() {
        return totalFees;
    }
}