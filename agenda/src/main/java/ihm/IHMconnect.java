package ihm;


import data.Constants;
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

    public IHMconnect(){
        createIhm();
    }

    // IHM
    private void createIhm(){
        // FENETRE PRINCIPALE
        window = new JFrame(Constants.appTitleConnect);
        window.setBounds(200,200,400,150);
        window.setLayout(new GridLayout(4,0));

        // FORM
        textLogin = new JPlaceholderTextField(Constants.phLogin);
        textPassword = new JPlaceholderTextField(Constants.phPassword);
        labelInfo = new JLabel("", JLabel.CENTER);
        labelInfo.setForeground(Color.red);
        buttonConnect = new JButton(Constants.btnConnect);
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
        if(textLogin.getText().compareTo(Constants.phLogin) == 0 || textPassword.getText().compareTo(Constants.phPassword) == 0){
            labelInfo.setText(Constants.errorFillForm);
        }
        else if(db.checkUserConnection(textLogin.getText(), textPassword.getText()) == null){
            labelInfo.setText(Constants.errorLogin);
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
