package Rooms;
import java.util.ArrayList;

public abstract class Room{
    protected String name;
    protected int ID, number_of_visitors;
    private ArrayList<Slot> slots = new ArrayList<>();
//    private ArrayList<Visitor> visitors = new ArrayList<>();


    public Room(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public void addSlot(Slot slot) {
        this.slots.add(slot);
    }

    public abstract void addVisitor(String visitor);
}



