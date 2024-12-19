package Visitors;

public class Instructor extends Visitor {
    public Instructor(String name, String password, String visitorType) {
        super(name, password, visitorType);
        super.totalReservedHours = 0;
    }
    public String toString() {
        return "ID: " + getId() + ", Name: " + getName() + ", Password: " + getPassword();
    }

    public Visitor rewardSys() {
        if (this.totalReservedHours % 12 == 0 && this.totalReservedHours != 0) {
            this.totalFreeHours++;
            return this;
        }
        return null;
    }


}
