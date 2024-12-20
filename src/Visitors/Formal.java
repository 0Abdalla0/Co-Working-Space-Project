package Visitors;


public class Formal extends Visitor {
    public Formal(String name, String password, String visitorType) {
        super(name, password, visitorType);
        super.totalReservedHours = 0;
    }
    public Visitor rewardSys() {
        if (this.totalReservedHours % 6 == 0 && this.totalReservedHours != 0) {
            this.totalFreeHours++;
            return this;
        }
        return null;
    }

}
