package Visitors;

public class General extends Visitor {
    public General(String name, String password, String visitorType) {
        super(name, password, visitorType);
        super.totalReservedHours = 0;
    }

    public Visitor rewardSys() {
        if (this.totalReservedHours >= 6) {
            return this;
        }
        return null;
    }


}
