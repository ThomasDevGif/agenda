package data;

import model.Course;
import model.Person;
import model.Room;
import model.Class;
import org.javalite.activejdbc.Base;

import java.util.List;

public class Database {

    private Base base;

    public Database() {
        connect();
    }

    /**
     * Connection to data
     */
    private void connect() {
        try{
            String url = "jdbc:postgresql://localhost:5432/agenda";
            String user = "postgres";
            String password = "postgres";
            base.open("org.postgresql.Driver", url, user, password);
            System.out.println("Connection done !");
        }
        catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Check user data
     */
    public Person checkUserConnection(String login, String password) {
        List<Person> persons = Person.where("login = ? and password = ?", login, password);
        Person person = null;
        if(persons != null && persons.size() > 0){
            person = persons.get(0);
        }
        return person;
    }

    // Person

    /**
     * Create a person in db
     */
    public void createUser(String login, String password, String firstname, String lastname, String email, int type){
        Person person = new Person();
        person.setId(Person.count()+1);
        person.setLogin(login);
        person.setPassword(password);
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setEmail(email);
        person.setType(type);
        person.insert();
    }

    /**
     * Get user in databse using his login
     */
    public Person getUserByLogin(String login){
        List<Person> persons = Person.where("login = ?", login);
        Person person = null;
        if(persons != null && persons.size() > 0){
            person = persons.get(0);
        }
        return person;
    }

    /**
     * Get all teachers from db
     * @param type Teacher type
     * @return List of teachers
     */
    public List<Person> getUsersByType(int type){
        return Person.where("type = ?", type);
    }

    // Class

    /**
     * Get all classes from db
     * @return List of classes
     */
    public List<Class> getAllClasses(){
        return Class.findAll();
    }

    // Course

    /**
     * Get all courses from db
     * @return List of rooms
     */
    public List<Course> getAllCourses(){
        return Course.findAll();
    }

    /**
     * Create a course in db
     */
    public void createCourse(int personId, int classId, int roomId, String subject, String date, int startHour){
        Course course = new Course();
        course.set("id", Course.count()+1);
        course.set("personId", personId);
        course.set("classId", classId);
        course.set("roomId", roomId);
        course.set("subject", subject);
        course.set("date", date);
        course.set("hour", startHour);
        course.insert();
    }

    // Room

    /**
     * Get all rooms from db
     * @return List of rooms
     */
    public List<Room> getAllRooms(){
        return Room.findAll();
    }

    // General

    /**
     * Create some data in db
     */
    public void populateDatabase(){
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
        person3.set("login", "student");
        person3.set("password", "password");
        person3.set("firstname", "Student");
        person3.set("lastname", "STUDENT");
        person3.set("email", "student@epsi.fr");
        person3.set("classId", 1);
        person3.set("type", 3);
        person3.insert();

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

        Class classToCreate = new Class();
        classToCreate.set("id", 1);
        classToCreate.set("label", "B3 - G1");
        classToCreate.insert();

        Class classToCreate2 = new Class();
        classToCreate2.set("id", 2);
        classToCreate2.set("label", "B3 - G2");
        classToCreate2.insert();

        System.out.println("Some data created !");
    }

    /**
     * Disconnect data
     */
    public void disconnect(){
        base.close();
        System.out.println("Connection closed.");
    }
}