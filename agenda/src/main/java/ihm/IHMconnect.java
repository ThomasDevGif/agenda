package ihm;


import data.Database;
import model.Person;
import util.JPlaceholderTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHMconnect implements ActionListener {

    // Variables
    private JFrame window;
    private JPlaceholderTextField textLogin;
    private JPlaceholderTextField textPassword;
    private JLabel labelInfo;
    private JButton buttonConnect;
    private String phLogin = "Identifiant";
    private String phPassword = "Mot de passe";
    private Database connect;

    public IHMconnect(){
        createIhm();
    }

    // IHM
    private void createIhm(){
        // FENETRE PRINCIPALE
        window = new JFrame("Agenda - Connexion");
        window.setBounds(200,200,400,150);
        window.setLayout(new GridLayout(4,0));

        // FORM
        textLogin = new JPlaceholderTextField(phLogin);
        textPassword = new JPlaceholderTextField(phPassword);
        labelInfo = new JLabel("", JLabel.CENTER);
        labelInfo.setForeground(Color.red);
        buttonConnect = new JButton("Se connecter");
        window.getContentPane().add(textLogin);
        window.getContentPane().add(textPassword);
        window.getContentPane().add(labelInfo);
        window.getContentPane().add(buttonConnect);

        // EVENTS
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonConnect.addActionListener(this);
        window.setVisible(true);
    }

    // INTERFACE ACTION LISTENER
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonConnect) {
            attemptLogin();
        }
    }

    /**
     * Check if user exists in db and then open app
     */
    private void attemptLogin(){
        // Connect to data
        Database db = new Database();
        // Check if input not empty
        if(textLogin.getText().compareTo(phLogin) == 0 || textPassword.getText().compareTo(phPassword) == 0){
            labelInfo.setText("Erreur, veuillez saisir tous les champs.");
        }
        else if(db.checkUserConnection(textLogin.getText(), textPassword.getText()) == null){
            labelInfo.setText("Erreur, login incorrect.");
        }
        // Close IHMconnect and display IHMagenda
        else{
            Person person = db.checkUserConnection(textLogin.getText(), textPassword.getText());
            System.out.println(person.toString());
            window.setVisible(false);
            window.dispose();
            new IHMhome(db, person);
        }
    }
}
