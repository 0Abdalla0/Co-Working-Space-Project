import User.user;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String name,password,visitorType;
        ArrayList<user> users = new ArrayList<user>();
        user.startMenu(users);
        for (user user : users) {
            System.out.println(user.getVisitorType());
        }
    }
}