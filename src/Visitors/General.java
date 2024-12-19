package Visitors;

public class General extends Visitor {
    public General(String name, String password, String visitorType) {
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
