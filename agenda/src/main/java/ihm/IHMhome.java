package ihm;

import data.Database;
import model.Person;
import data.Constants;
import ihm.calendar.IHMcalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IHMhome implements ActionListener {

    // Variables
    private JFrame window;
    private JButton menuItemCreateUSer;
    private JButton menuItemDisplayPlanning;
    private JButton menuItemUpdatePlanning;
    private JButton menuItemDisconnect;
    private Database database;
    private Person person;

    public IHMhome(Database database, Person person) {
        this.database = database;
        this.person = person;
        createIHM();
    }

    /**
     * Create interface
     */
    private void createIHM(){
        int type = person.getType();
        // Frame
        if(type == 1){
            window = new JFrame(Constants.appTitleAdmin);
        } else if(type == 2){
            window = new JFrame(Constants.appTitleTeacher);
        } else if(type == 3){
            window = new JFrame(Constants.appTitleStudent);
        }
        window.setBounds(200,200,650,500);

        // Buttons
        menuItemDisplayPlanning = new JButton(Constants.itemDisplayPlanning);
        menuItemUpdatePlanning = new JButton(Constants.itemUpdatePlanning);
        menuItemCreateUSer = new JButton(Constants.itemCreateAccount);
        menuItemDisconnect = new JButton(Constants.itemDisconnect);
        menuItemDisplayPlanning.addActionListener(this);
        menuItemUpdatePlanning.addActionListener(this);
        menuItemCreateUSer.addActionListener(this);
        menuItemDisconnect.addActionListener(this);

        JPanel panelMenu = new JPanel(new GridLayout(4, 0));
        panelMenu.add(menuItemDisplayPlanning);
        if(type == 1){
            panelMenu.add(menuItemUpdatePlanning);
            panelMenu.add(menuItemCreateUSer);
        }
        panelMenu.add(menuItemDisconnect);

        // Add items
        window.getContentPane().add(panelMenu, BorderLayout.CENTER);

        // Events
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * Close database connection and display IHMconnect
     */
    private void disconnect(){
        window.dispose();
        database.disconnect();
        new IHMconnect();
    }

    /**
     * Buttons click listener
     * @param e Event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemDisconnect) {
            disconnect();
        } else if(e.getSource() == menuItemCreateUSer){
            new IHMcreateUser(database);
        } else if(e.getSource() == menuItemDisplayPlanning){
            new IHMcalendar(database, person);
        } else if(e.getSource() == menuItemUpdatePlanning){
            new IHMcreateCourse(database);
        }
    }
}
