package data;

import model.*;
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

    /**
     * Get user by it's id
     * @param personId Person id
     * @return Person
     */
    public Person getUserById(int personId){
        return Person.findFirst("id = ?", personId);
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
    public void createCourse(int personId, int classId, int roomId, int subjectId, String date, int startHour){
        Course course = new Course();
        course.set("id", Course.count()+1);
        course.set("personId", personId);
        course.set("classId", classId);
        course.set("roomId", roomId);
        course.set("subjectId", subjectId);
        course.set("date", date);
        course.set("hour", startHour);
        course.insert();
    }

    /**
     * Check if there isn't a course at this moment for this class
     * @param classId Class id
     * @param date Date of course
     * @param startHour Hour of course
     * @return List of courses
     */
    public List<Course> getCoursesByDateAndClass(int classId, String date, int startHour){
        return Course.where("classId = ? and date = ? and hour = ?", classId, date, startHour);
    }

    /**
     * Check if there isn't a course at this moment for this class
     * @param personId Teacher id
     * @param date Date of course
     * @param startHour Hour of course
     * @return List of courses
     */
    public List<Course> getCoursesByDateAndPerson(int personId, String date, int startHour){
        return Course.where("personId = ? and date = ? and hour = ?", personId, date, startHour);
    }

    /**
     * Check if the teacher has not a course at this moment
     * @param roomId Room id
     * @param date Date of course
     * @param startHour Hour of course
     * @return Boolean
     */
    public List<Course> getCoursesByDateAndRoom(int roomId, String date, int startHour){
        return Course.where("roomId = ? and date = ? and hour = ?", roomId, date, startHour);
    }

    /**
     * Get courses of a person at a day
     * @param personId Teacher id
     * @param date Date of course
     * @return List of courses
     */
    public List<Course> getCoursesByDayAndPerson(int personId, String date){
        return Course.where("personId = ? and date = ?", personId, date);
    }

    /**
     * Get courses of a person at a day
     * @param classId Student class id
     * @param date Date of course
     * @return List of courses
     */
    public List<Course> getCoursesByDayAndClass(int classId, String date){
        return Course.where("classId = ? and date = ?", classId, date);
    }

    // Room

    /**
     * Get all rooms from db
     * @return List of rooms
     */
    public List<Room> getAllRooms(){
        return Room.findAll();
    }

    /**
     * Get room by it's id
     * @param roomId Room id
     * @return Room
     */
    public Room getRoomById(int roomId){
        return Room.findFirst("id = ?", roomId);
    }

    // Subject

    /**
     * Get all subjects from db
     * @return List of subjects
     */
    public List<Subject> getAllSubjects() {
        return Subject.findAll().orderBy("label");
    }

    /**
     * Get sibject by it's id
     * @param subjectId Subject id
     * @return Subject
     */
    public Subject getSubjectById(int subjectId){
        return Subject.findFirst("id = ?", subjectId);
    }

    // General

    /**
     * Create some data in db
     */
    public void populateDatabase(){
        new PopulateDB();
    }

    /**
     * Disconnect data
     */
    public void disconnect(){
        base.close();
        System.out.println("Connection closed.");
    }
}