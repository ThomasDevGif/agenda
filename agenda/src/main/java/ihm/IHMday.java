package ihm;

import data.Database;
import ihm.calendar.IHMcalendar;
import model.Course;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IHMday implements ActionListener {

    // Variables
    private Database database;
    private Person person;
    private List<Course> mListCourses;
    // View
    private JFrame window;
    private String date;
    private JButton buttonBack;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JPanel panel14;
    private JPanel panel15;
    private JPanel panel16;
    private JPanel panel17;
    private JPanel panel18;

    public IHMday(Database database, Person person, String day, String month, String year){
        this.database = database;
        this.person = person;
        this.date = dateFormat(day, month, year);
        initializePanels();
        initializeCourses();
        createIHM();
    }

    /**
     * Initialize all panels to display courses
     */
    private void initializePanels(){
        panel8 = new JPanel(new GridLayout(2, 0));
        panel9 = new JPanel(new GridLayout(2, 0));
        panel10 = new JPanel(new GridLayout(2, 0));
        panel11 = new JPanel(new GridLayout(2, 0));
        panel12 = new JPanel(new GridLayout(2, 0));
        panel13 = new JPanel(new GridLayout(2, 0));
        panel14 = new JPanel(new GridLayout(2, 0));
        panel15 = new JPanel(new GridLayout(2, 0));
        panel16 = new JPanel(new GridLayout(2, 0));
        panel17 = new JPanel(new GridLayout(2, 0));
        panel18 = new JPanel(new GridLayout(2, 0));
        panel8.setBorder(BorderFactory.createLineBorder(Color.black));
        panel9.setBorder(BorderFactory.createLineBorder(Color.black));
        panel10.setBorder(BorderFactory.createLineBorder(Color.black));
        panel11.setBorder(BorderFactory.createLineBorder(Color.black));
        panel12.setBorder(BorderFactory.createLineBorder(Color.black));
        panel13.setBorder(BorderFactory.createLineBorder(Color.black));
        panel14.setBorder(BorderFactory.createLineBorder(Color.black));
        panel15.setBorder(BorderFactory.createLineBorder(Color.black));
        panel16.setBorder(BorderFactory.createLineBorder(Color.black));
        panel17.setBorder(BorderFactory.createLineBorder(Color.black));
        panel18.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Get and display courses for a person at a date
     */
    private void initializeCourses(){
        if(person.getType() == 1 || person.getType() == 2){ // Admin or teacher
            mListCourses = database.getCoursesByDayAndPerson((Integer) person.get("id"), date);
        }
        else{ // Student
            mListCourses = database.getCoursesByDayAndClass(person.getClassId(), date);
        }

        JLabel labelCourseSubject = new JLabel();
        JLabel labelCourseTeacher = new JLabel();
        labelCourseSubject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCourseTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        for(Course course : mListCourses){
            // Display subject
            labelCourseSubject.setText(course.getSubject() + " - " + course.getClassId());
            labelCourseTeacher.setText(String.valueOf(course.getPersonId()));
            switch (course.getHour()){
                case 8: {
                    panel8.add(labelCourseSubject);
                    panel8.add(labelCourseTeacher);
                    break;
                }
                case 9: {
                    panel9.add(labelCourseSubject);
                    panel9.add(labelCourseTeacher);
                    break;
                }
                case 10: {
                    panel10.add(labelCourseSubject);
                    panel10.add(labelCourseTeacher);
                    break;
                }
                case 11: {
                    panel11.add(labelCourseSubject);
                    panel11.add(labelCourseTeacher);
                    break;
                }
                case 12: {
                    panel12.add(labelCourseSubject);
                    panel12.add(labelCourseTeacher);
                    break;
                }
                case 13: {
                    panel13.add(labelCourseSubject);
                    panel13.add(labelCourseTeacher);
                    break;
                }
                case 14: {
                    panel14.add(labelCourseSubject);
                    panel14.add(labelCourseTeacher);
                    break;
                }
                case 15: {
                    panel15.add(labelCourseSubject);
                    panel15.add(labelCourseTeacher);
                    break;
                }
                case 16: {
                    panel16.add(labelCourseSubject);
                    panel16.add(labelCourseTeacher);
                    break;
                }
                case 17: {
                    panel17.add(labelCourseSubject);
                    panel17.add(labelCourseTeacher);
                    break;
                }
                case 18: {
                    panel18.add(labelCourseSubject);
                    panel18.add(labelCourseTeacher);
                    break;
                }
            }
        }
    }

    /**
     * Create and display IHM
     */
    private void createIHM(){
        window = new JFrame();
        window.setBounds(200,200,400,500);

        // Title
        JLabel labelTitle = new JLabel(date, SwingConstants.CENTER);

        // Content
        JPanel contentPanel = new JPanel(new GridLayout(0, 2));
        JLabel labelHours = new JLabel("8h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel8);

        labelHours = new JLabel("9h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel9);

        labelHours = new JLabel("10h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel10);

        labelHours = new JLabel("11h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel11);

        labelHours = new JLabel("12h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel12);

        labelHours = new JLabel("13h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel13);

        labelHours = new JLabel("14h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel14);

        labelHours = new JLabel("15h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel15);

        labelHours = new JLabel("16h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel16);

        labelHours = new JLabel("17h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel17);

        labelHours = new JLabel("18h");
        labelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contentPanel.add(labelHours);
        contentPanel.add(panel18);

        // Return button
        buttonBack = new JButton("Retour");
        buttonBack.addActionListener(this);

        // Add content
        window.getContentPane().add(labelTitle, BorderLayout.NORTH);
        window.getContentPane().add(contentPanel, BorderLayout.CENTER);
        window.getContentPane().add(buttonBack, BorderLayout.SOUTH);

        // Disable window buttons
        window.setUndecorated(true);
        window.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        window.setVisible(true);
    }

    /**
     * Create date format dd/mm/yyyy
     * @param day Day
     * @param month Month
     * @param year Year
     * @return date
     */
    private String dateFormat(String day, String month, String year){
        String date;
        if(day.length() <= 1){
            day = "0"+day;
        }
        if(month.length() <= 1){
            month = "0"+month;
        }
        date = day+"/"+month+"/"+year;
        return date;
    }

    /**
     * Buttons click listener
     * @param e Event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonBack) {
            window.dispose();
            new IHMcalendar(database, person);
        }
    }
}
