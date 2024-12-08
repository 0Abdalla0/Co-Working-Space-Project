import User.user;
import Visitors.Formal;
import Visitors.General;
import Visitors.Instructor;
import Visitors.Visitor;

import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {
        // NOTE : ANY ARRAYLIST WILL BE IN MAIN AND TAKE PARAMETERS OF IT
        String name,password,visitorType;
        ArrayList<user> users = new ArrayList<user>();
        user.startMenu(users);
        // //////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Formal> formals = new ArrayList<>();
        ArrayList<General> generals = new ArrayList<>();
        ArrayList<Instructor> instructors = new ArrayList<>();
        // ////////////////////////////////////////////////////////////////////////////
        // Debugging: Print out registered users
        System.out.println("Total registered users: " + users.size());
        Visitor visitor = new Visitor();
        for (user user : users) {
            visitor.sortVisitors(user,formals,generals,instructors);
        }
        System.out.println("Formal visitors: " + formals.size());
        System.out.println("General visitors: " + generals.size());
        System.out.println("Instructor visitors: " + instructors.size());

    }
}