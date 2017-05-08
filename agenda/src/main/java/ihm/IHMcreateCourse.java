package ihm;

import data.Constants;
import data.Database;
import model.Class;
import model.Course;
import model.Person;
import model.Room;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateLabelFormatter;
import util.JPlaceholderTextField;

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
    private JPlaceholderTextField textFieldSubject;
    private JComboBox comboBoxClass;
    private JComboBox comboBoxRoom;
    private JDatePickerImpl datePicker;
    private JComboBox comboBoxHour;
    // Data
    private Database database;
    private List<String> mListTeachers;
    private List<Person> mTeachers;
    private List<String> mListClasses;
    private List<Class> mClasses;
    private List<String> mListRooms;
    private List<Room> mRooms;
    private List<Integer> mListHours;
    private List<Course> mListCourses;

    /**
     * Constructor
     */
    public IHMcreateCourse(Database database) {
        this.database = database;
        initializeTeacher();
        initializeClasses();
        initializeRooms();
        initializeHours();
        initializeCourses();
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
     * Fill list of hours
     */
    private void initializeHours(){
        mListHours = new ArrayList<Integer>();
        for(int i=8; i<19; i++){
            mListHours.add(i);
        }
    }

    /**
     * Fill list of courses
     */
    private void initializeCourses(){
        mListCourses = database.getAllCourses();
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
        textFieldSubject = new JPlaceholderTextField(Constants.phSubject);

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

        // Error message
        labelInfo = new JLabel();
        labelInfo.setForeground(Color.red);
        // Validate button
        buttonValidate = new JButton("Valider");
        buttonValidate.addActionListener(this);

        jpanel.add(labelTeacher);
        jpanel.add(comboBoxTeacher);
        jpanel.add(labelClass);
        jpanel.add(comboBoxClass);
        jpanel.add(labelSubject);
        jpanel.add(textFieldSubject);
        jpanel.add(labelRoom);
        jpanel.add(comboBoxRoom);
        jpanel.add(labelDate);
        jpanel.add(datePicker);
        jpanel.add(labelHour);
        jpanel.add(comboBoxHour);
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
        if(datePicker.getJFormattedTextField().getText().compareTo("") == 0 &&
                textFieldSubject.getText().compareTo(Constants.phSubject) == 0){
            labelInfo.setText("Erreur, veuillez saisir tous les champs.");
        } else { // TODO Check if there isn't already a course + reset form
            // Id in db of selected teacher
            int personId = (Integer) mTeachers.get(mListTeachers.indexOf(comboBoxTeacher.getSelectedItem())).get("id");
            // Id in db of selected class
            int classId = (Integer) mClasses.get(mListClasses.indexOf(comboBoxClass.getSelectedItem())).get("id");
            // Id in db of selected room
            int roomId = (Integer) mRooms.get(mListRooms.indexOf(comboBoxRoom.getSelectedItem())).get("id");
            String subject = textFieldSubject.getText();
            String date = datePicker.getJFormattedTextField().getText();
            int startHour = (Integer) comboBoxHour.getSelectedItem();
            database.createCourse(personId, classId, roomId, subject, date, startHour);
            resetForm();
        }
    }

    /**
     * Reset subject and date input
     */
    private void resetForm(){
        textFieldSubject.setText("");
        datePicker.getJFormattedTextField().setText("");
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
