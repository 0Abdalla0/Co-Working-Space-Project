package Visitors;

import java.util.ArrayList;

public class General extends Visitor {
//    private int hours;
    public General(String name, String password, String visitorType) {
        super(name, password, visitorType);
        super.totalReservedHours = 0;
//        this.hours = 0;
    }

    public Visitor rewardSys() {
        if (this.totalReservedHours >= 6) {
            return this;
        }
        return null;
    }


}
