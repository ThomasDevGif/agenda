package ihm;

import data.Constants;
import data.Database;
import model.*;
import model.Class;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IHMcreateCourse implements ActionListener {

    // Variables
    private JLabel labelInfo;
    private JButton buttonValidate;
    private JFrame window;
    private JComboBox comboBoxTeacher;
    private JComboBox comboBoxSubject;
    private JComboBox comboBoxClass;
    private JComboBox comboBoxRoom;
    private JDatePickerImpl datePicker;
    private JComboBox comboBoxHour;
    private JComboBox comboBoxDuration;
    // Data
    private Database database;
    private List<String> mListTeachers;
    private List<Person> mTeachers;
    private List<String> mListClasses;
    private List<Class> mClasses;
    private List<String> mListRooms;
    private List<Room> mRooms;
    private List<String> mListSubjects;
    private List<Subject> mSubjects;
    private List<Integer> mListHours;
    private List<Integer> mListDurations;

    /**
     * Constructor
     */
    public IHMcreateCourse(Database database) {
        this.database = database;
        initializeTeacher();
        initializeClasses();
        initializeRooms();
        initializeSubjects();
        initializeHours();
        initializeDurations();
        createIhm();
    }

    /**
     * Fill list of teachers
     */
    private void initializeTeacher(){
        mListTeachers = new ArrayList<String>();
        mTeachers = database.getUsersByType(2);
        for(Person teacher : mTeachers){
            mListTeachers.add(teacher.getFirstname() + " " + teacher.getLastname());
        }
    }

    /**
     * Fill list of classes
     */
    private void initializeClasses(){
        mListClasses = new ArrayList<String>();
        mClasses = database.getAllClasses();
        for(Class classe : mClasses){
            mListClasses.add(classe.getLabel());
        }
    }

    /**
     * Fill list of rooms
     */
    private void initializeRooms(){
        mListRooms = new ArrayList<String>();
        mRooms = database.getAllRooms();
        for(Room room : mRooms){
            mListRooms.add(room.getLabel());
        }
    }

    /**
     * Fill list of subjects
     */
    private void initializeSubjects(){
        mListSubjects = new ArrayList<String>();
        mSubjects = database.getAllSubjects();
        for(Subject subject : mSubjects){
            mListSubjects.add(subject.getLabel());
        }
    }

    /**
     * Fill list of hours
     */
    private void initializeHours(){
        mListHours = new ArrayList<Integer>();
        for(int i=8; i<19; i++){
            mListHours.add(i);
        }
    }

    /**
     * Fill list of hours
     */
    private void initializeDurations(){
        mListDurations = new ArrayList<Integer>();
        for(int i=1; i<5; i++){
            mListDurations.add(i);
        }
    }

    /**
     * Initialize interface
     */
    private void createIhm(){
        window = new JFrame("Créer un cours");
        window.setBounds(200,200,650,500);
        JPanel jpanel = new JPanel(new GridLayout(0, 2));

        // Teacher
        JLabel labelTeacher = new JLabel("Professeur");
        comboBoxTeacher = new JComboBox(mListTeachers.toArray());

        // Classes
        JLabel labelClass = new JLabel("Classe");
        comboBoxClass = new JComboBox(mListClasses.toArray());

        // Subject
        JLabel labelSubject = new JLabel("Matière");
        comboBoxSubject = new JComboBox(mListSubjects.toArray());

        // Room
        JLabel labelRoom = new JLabel("Salle");
        comboBoxRoom = new JComboBox(mListRooms.toArray());

        // DatePicker
        JLabel labelDate = new JLabel("Date");
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Hour
        JLabel labelHour = new JLabel("Heure");
        comboBoxHour = new JComboBox(mListHours.toArray());

        JLabel labelDuration = new JLabel("Durée");
        comboBoxDuration = new JComboBox(mListDurations.toArray());

        // Info message
        labelInfo = new JLabel();
        // Validate button
        buttonValidate = new JButton("Valider");
        buttonValidate.addActionListener(this);

        jpanel.add(labelTeacher);
        jpanel.add(comboBoxTeacher);
        jpanel.add(labelClass);
        jpanel.add(comboBoxClass);
        jpanel.add(labelSubject);
        jpanel.add(comboBoxSubject);
        jpanel.add(labelRoom);
        jpanel.add(comboBoxRoom);
        jpanel.add(labelDate);
        jpanel.add(datePicker);
        jpanel.add(labelHour);
        jpanel.add(comboBoxHour);
        jpanel.add(labelDuration);
        jpanel.add(comboBoxDuration);
        jpanel.add(labelInfo);
        jpanel.add(buttonValidate);
        window.getContentPane().add(jpanel, BorderLayout.CENTER);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * Create course in db
     */
    private void createCourse(){
        if(datePicker.getJFormattedTextField().getText().compareTo("") == 0){
            labelInfo.setText(Constants.errorFillForm);
        } else {
            // Id in db of selected teacher
            int personId = (Integer) mTeachers.get(mListTeachers.indexOf(comboBoxTeacher.getSelectedItem())).get("id");
            // Id in db of selected class
            int classId = (Integer) mClasses.get(mListClasses.indexOf(comboBoxClass.getSelectedItem())).get("id");
            // Id in db of selected room
            int roomId = (Integer) mRooms.get(mListRooms.indexOf(comboBoxRoom.getSelectedItem())).get("id");
            // Id in db of selected subject
            int subjectId = (Integer) mSubjects.get(mListSubjects.indexOf(comboBoxSubject.getSelectedItem())).get("id");
            String date = datePicker.getJFormattedTextField().getText();
            int startHour = (Integer) comboBoxHour.getSelectedItem();
            if(!checkValidClass(classId, date, startHour)){
                setErrorMessage(Constants.errorDateClass);
            }
            else if(!checkValidTeacher(personId, date, startHour)){
                setErrorMessage(Constants.errorDateTeacher);
            }
            else if(!checkValidRoom(roomId, date, startHour)){
                setErrorMessage(Constants.errorDateRoom);
            }
            else {
                for(int i=0; i<(Integer) comboBoxDuration.getSelectedItem(); i++){
                    database.createCourse(personId, classId, roomId, subjectId, date, startHour+i);
                }
                resetForm();
                setSuccessMessage(Constants.successCreateCourse);
            }
        }
    }

    /**
     * Check if there isn't a course at this moment for this class
     * @param classId Class id
     * @param date Date of course
     * @param startHour Hour of course
     * @return Boolean
     */
    private boolean checkValidClass(int classId, String date, int startHour){
        List<Course> courses = database.getCoursesByDateAndClass(classId, date, startHour);
        if(courses == null || courses.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the teacher has not a course at this moment
     * @param personId Teacher id
     * @param date Date of course
     * @param startHour Hour of course
     * @return Boolean
     */
    private boolean checkValidTeacher(int personId, String date, int startHour){
        List<Course> courses = database.getCoursesByDateAndPerson(personId, date, startHour);
        if(courses == null || courses.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the teacher has not a course at this moment
     * @param roomId Room id
     * @param date Date of course
     * @param startHour Hour of course
     * @return Boolean
     */
    private boolean checkValidRoom(int roomId, String date, int startHour){
        List<Course> courses = database.getCoursesByDateAndRoom(roomId, date, startHour);
        if(courses == null || courses.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset subject and date input
     */
    private void resetForm(){
        datePicker.getJFormattedTextField().setText("");
    }

    /**
     * Display an successs message to the user
     * @param message Success message
     */
    private void setSuccessMessage(String message){
        labelInfo.setForeground(Color.GREEN);
        labelInfo.setText(message);
    }

    /**
     * Display an error message to the user
     * @param message Error message
     */
    private void setErrorMessage(String message){
        labelInfo.setForeground(Color.RED);
        labelInfo.setText(message);
    }

    /**
     * Buttons click listener
     * @param e Event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonValidate) {
            createCourse();
        }
    }
}
