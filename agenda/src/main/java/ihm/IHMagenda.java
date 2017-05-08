package ihm;

import data.Database;
import ihm.IHMconnect;
import util.JPlaceholderTextField;
import model.Person;
import data.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHMagenda implements ActionListener {

    // Variables
    private Database database;
    private JButton menuItemDisconnect;
    private JButton buttonCreateUser;
    private JFrame window;
    // Form create user
    private JPlaceholderTextField inputLogin;
    private JPlaceholderTextField inputPassword;
    private JPlaceholderTextField inputFirstname;
    private JPlaceholderTextField inputLastname;
    private JPlaceholderTextField inputEmail;

    public IHMagenda(Database database){
        this.database = database;
    }

    // IHM
    public void createIhm(Person person){
        int type = person.getType();
        // Frame
        if(type == 1){
            window = new JFrame(Constants.appTitleAdmin);
        } else{
            window = new JFrame(Constants.appTitle);
        }
        window.setBounds(200,200,650,500);

        // Menu
        JTabbedPane panelMenu = new JTabbedPane();
        JPanel cardCreateAccount = initializeCreateAccountView();
        JPanel cardUpdatePlanning = initializeUpdatePlanningView();
        JPanel cardDisplayPlanning = initializeDisplayPlanningView();
        menuItemDisconnect = new JButton(Constants.itemDisconnect);
        menuItemDisconnect.addActionListener(this);

        panelMenu.addTab(Constants.itemDisplayPlanning, cardDisplayPlanning);
        if(type == 1){
            panelMenu.addTab(Constants.itemUpdatePlanning, cardUpdatePlanning);
            panelMenu.addTab(Constants.itemCreateAccount, cardCreateAccount);
        }
        panelMenu.add(menuItemDisconnect);

        // Add items
        window.getContentPane().add(panelMenu, BorderLayout.NORTH);

        // Events
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * Display form to create an account teacher
     * @return JPanel
     */
    private JPanel initializeCreateAccountView(){
        JPanel jpanel = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        jpanel.setLayout(new GridLayout(7,0));
        JLabel titleCreateAccount = new JLabel("Créer un compte professeur", JLabel.CENTER);
        inputLogin = new JPlaceholderTextField(Constants.phLogin);
        inputPassword = new JPlaceholderTextField(Constants.phPassword);
        inputFirstname = new JPlaceholderTextField(Constants.phFirstname);
        inputLastname = new JPlaceholderTextField(Constants.phLastname);
        inputEmail = new JPlaceholderTextField(Constants.phEmail);
        buttonCreateUser = new JButton("Valider");
        buttonCreateUser.addActionListener(this);
        jpanel.add(titleCreateAccount);
        jpanel.add(inputLogin);
        jpanel.add(inputPassword);
        jpanel.add(inputFirstname);
        jpanel.add(inputLastname);
        jpanel.add(inputEmail);
        jpanel.add(buttonCreateUser);
        return jpanel;
    }

    /**
     * Display view to update the planning
     * @return JPanel
     */
    private JPanel initializeUpdatePlanningView(){
        JPanel jpanel = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        return jpanel;
    }

    /**
     * Display view to take a look at the planning
     * @return JPanel
     */
    private JPanel initializeDisplayPlanningView(){
        JPanel jpanel = new JPanel(new BorderLayout()){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        return jpanel;
    }

    /**
     * Create a teacher in db
     */
    private void addTeacher(){
        database.createUser(inputLogin.getText(),
                inputPassword.getText(),
                inputFirstname.getText(),
                inputLastname.getText(),
                inputEmail.getText(),
                2);
        resetInput();
    }

    /**
     * Remove text of textfield
     */
    private void resetInput(){
        inputLogin.setText("");
        inputPassword.setText("");
        inputFirstname.setText("");
        inputLastname.setText("");
        inputEmail.setText("");
    }

    /**
     * Close IHMagenda and display IHMconnect
     */
    private void disconnect(){
        window.dispose();
        new IHMconnect();
        database.disconnect();
    }

    /**
     * Buttons click listener
     * @param e Event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemDisconnect) {
            disconnect();
        } else if(e.getSource() == buttonCreateUser){
            addTeacher();
        }
    }

}
