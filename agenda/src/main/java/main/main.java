package main;

import data.Database;
import ihm.IHMconnect;
import ihm.IHMcreateCourse;
import ihm.IHMcreateUser;
import ihm.calendar.IHMcalendar;

public class main {
    public static void main(String[] args) {
//        Database db = new Database();
//        db.populateDatabase();

        new IHMconnect();
//        new IHMcalendar();
//        new IHMcreateUser(db);
//        new IHMcreateCourse(db);
    }
}
