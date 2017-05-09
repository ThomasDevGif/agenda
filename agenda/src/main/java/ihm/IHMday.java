package ihm;

import data.Database;
import ihm.calendar.IHMcalendar;
import model.Course;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        initializeCourses();
        createIHM();
    }

    /**
     * Get and display courses for a person at a date
     */
    private void initializeCourses(){
        mListCourses = database.getCoursesByDayAndPerson((Integer) person.get("id"), date);
        panel8 = new JPanel();
        panel9 = new JPanel();

        JLabel labelCourseSubject = new JLabel();
        for(Course course : mListCourses){
            // Display subject
            labelCourseSubject.setText(course.getSubject());
            System.out.println("Course: " + course.getHour());

            switch (course.getHour()){
                case 8: {
                    System.out.println("8h !");
                    panel8.add(labelCourseSubject);
                    break;
                }
                case 9: {
                    System.out.println("9h !");
                    panel8.add(labelCourseSubject);
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
        window.setBounds(200,200,400,700);

        // Title
        JLabel labelTitle = new JLabel(date, SwingConstants.CENTER);

        // Content
        JPanel contentPanel = new JPanel(new GridLayout(0, 2));
        JLabel labelHours = new JLabel("8h");
        contentPanel.add(labelHours);
        contentPanel.add(panel8);

        labelHours = new JLabel("9h");
        panel9 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel9);

        labelHours = new JLabel("10h");
        panel10 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel10);

        labelHours = new JLabel("11h");
        panel11 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel11);

        labelHours = new JLabel("12h");
        panel12 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel12);

        labelHours = new JLabel("13h");
        panel13 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel13);

        labelHours = new JLabel("14h");
        panel14 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel14);

        labelHours = new JLabel("15h");
        panel15 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel15);

        labelHours = new JLabel("16h");
        panel16 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel16);

        labelHours = new JLabel("17h");
        panel17 = new JPanel();
        contentPanel.add(labelHours);
        contentPanel.add(panel17);

        labelHours = new JLabel("18h");
        panel18 = new JPanel();
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
