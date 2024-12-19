package Visitors;

import java.util.ArrayList;

public class General extends Visitor {
    private int hours;
    public General(String name, String password, String visitorType) {
        super(name, password, visitorType);
        this.hours = 0;
    }

    public Visitor rewardSys(int hours){
        hours= super.totalReservedHours;
        if ( hours >= 6){
            return this;
        }
        return null;
    }

}
