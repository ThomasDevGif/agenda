package ihm;

import data.Constants;
import data.Database;
import model.Person;
import util.JPlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHMcreateUser implements ActionListener {

    // Variables
    private JFrame window;
    private JLabel labelInfo;
    private JPlaceholderTextField inputLogin;
    private JPlaceholderTextField inputPassword;
    private JPlaceholderTextField inputFirstname;
    private JPlaceholderTextField inputLastname;
    private JPlaceholderTextField inputEmail;
    private JButton buttonCreateUser;
    private Database database;

    /**
     * Constructor
     */
    public IHMcreateUser(Database database){
        this.database = database;
        createIHM();
    }

    /**
     * Create interface
     */
    private void createIHM(){
        window = new JFrame("Création d'un professeur");
        window.setBounds(200,200,650,500);

        JPanel jpanel = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        jpanel.setLayout(new GridLayout(7,0));
        labelInfo = new JLabel("", JLabel.CENTER);
        labelInfo.setForeground(Color.red);
        inputLogin = new JPlaceholderTextField(Constants.phLogin);
        inputPassword = new JPlaceholderTextField(Constants.phPassword);
        inputFirstname = new JPlaceholderTextField(Constants.phFirstname);
        inputLastname = new JPlaceholderTextField(Constants.phLastname);
        inputEmail = new JPlaceholderTextField(Constants.phEmail);
        buttonCreateUser = new JButton("Valider");
        buttonCreateUser.addActionListener(this);
        jpanel.add(inputLogin);
        jpanel.add(inputPassword);
        jpanel.add(inputFirstname);
        jpanel.add(inputLastname);
        jpanel.add(inputEmail);
        jpanel.add(labelInfo);
        jpanel.add(buttonCreateUser);

        window.getContentPane().add(jpanel, BorderLayout.CENTER);
        // Events
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * Create a teacher in db
     */
    private void addTeacher(){
        if(inputLogin.getText().compareTo(Constants.phLogin) != 0 &&
                inputPassword.getText().compareTo(Constants.phPassword) != 0 &&
                inputFirstname.getText().compareTo(Constants.phFirstname) != 0 &&
                inputLastname.getText().compareTo(Constants.phLastname) != 0 &&
                inputEmail.getText().compareTo(Constants.phEmail) != 0) {

            Person person = database.getUserByLogin(inputLogin.getText());
            if(person == null){
                database.createUser(inputLogin.getText(),
                        inputPassword.getText(),
                        inputFirstname.getText(),
                        inputLastname.getText(),
                        inputEmail.getText(),
                        2);
                window.dispose();
            } else {
                showError("Cet identifiant existe déjà.");
            }
        } else {
            showError("Erreur, veuillez saisir tous les champs.");
        }
    }

    /**
     * Show error message to the user
     * @param error Error message
     */
    private void showError(String error){
        labelInfo.setText(error);
    }

    /**
     * Buttons click listener
     * @param e Event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCreateUser) {
            addTeacher();
        }
    }
}
