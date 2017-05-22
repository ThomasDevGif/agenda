package data;


import model.Class;
import model.Person;
import model.Room;
import model.Subject;

public class PopulateDB {

    public PopulateDB(){
        initializePerson();
        initializeRoom();
        initializeClass();
        initializeSubject();
    }

    /**
     * Create some person in db
     */
    private void initializePerson(){
        Person person = new Person();
        person.set("id", 1);
        person.set("login", "thomas");
        person.set("password", "password");
        person.set("firstname", "Thomas");
        person.set("lastname", "TOURNOUX");
        person.set("email", "thomas.tournoux@epsi.fr");
        person.set("classId", null);
        person.set("type", 1);
        person.insert();

        Person person2 = new Person();
        person2.set("id", 2);
        person2.set("login", "manue");
        person2.set("password", "password");
        person2.set("firstname", "Emmanuelle");
        person2.set("lastname", "CORREIA");
        person2.set("email", "emmanuelle.correia@epsi.fr");
        person2.set("classId", null);
        person2.set("type", 2);
        person2.insert();

        Person person3 = new Person();
        person3.set("id", 3);
        person3.set("login", "alexis");
        person3.set("password", "password");
        person3.set("firstname", "Alexis");
        person3.set("lastname", "VACHARD");
        person3.set("email", "alexis.vachard@epsi.fr");
        person3.set("classId", null);
        person3.set("type", 2);
        person3.insert();

        Person student = new Person();
        student.set("id", 4);
        student.set("login", "student");
        student.set("password", "password");
        student.set("firstname", "Student");
        student.set("lastname", "STUDENT");
        student.set("email", "student@epsi.fr");
        student.set("classId", 1);
        student.set("type", 3);
        student.insert();

        System.out.println("Some persons created.");
    }

    /**
     * Create some rooms in db
     */
    private void initializeRoom(){
        Room room = new Room();
        room.set("id", 1);
        room.set("label", "103");
        room.set("capacity", 30);
        room.insert();

        Room room2 = new Room();
        room2.set("id", 2);
        room2.set("label", "404");
        room2.set("capacity", 25);
        room2.insert();

        System.out.println("Some rooms created.");
    }

    /**
     * Create some classes in db
     */
    private void initializeClass(){
        Class classToCreate = new Class();
        classToCreate.set("id", 1);
        classToCreate.set("label", "B3 - G1");
        classToCreate.insert();

        Class classToCreate2 = new Class();
        classToCreate2.set("id", 2);
        classToCreate2.set("label", "B3 - G2");
        classToCreate2.insert();

        System.out.println("Some classes created.");
    }

    /**
     * Create some subjects
     */
    private void initializeSubject(){
        Subject android = new Subject();
        android.set("id", 1);
        android.set("label", "Android");
        android.set("red", 139);
        android.set("green", 195);
        android.set("blue", 74);
        android.insert();

        Subject windows = new Subject();
        windows.set("id", 2);
        windows.set("label", "Windows");
        windows.set("red", 0);
        windows.set("green", 0);
        windows.set("blue", 0);
        windows.insert();

        Subject linux = new Subject();
        linux.set("id", 3);
        linux.set("label", "Linux");
        linux.set("red", 66);
        linux.set("green", 66);
        linux.set("blue", 66);
        linux.insert();

        Subject java = new Subject();
        java.set("id", 4);
        java.set("label", "Java");
        java.set("red", 33);
        java.set("green", 150);
        java.set("blue", 243);
        java.insert();

        Subject anglais = new Subject();
        anglais.set("id", 5);
        anglais.set("label", "Anglais");
        anglais.set("red", 233);
        anglais.set("green", 30);
        anglais.set("blue", 99);
        anglais.insert();

        Subject francais = new Subject();
        francais.set("id", 6);
        francais.set("label", "Français");
        francais.set("red", 244);
        francais.set("green", 67);
        francais.set("blue", 54);
        francais.insert();

        System.out.println("Some subjects created.");
    }
}
