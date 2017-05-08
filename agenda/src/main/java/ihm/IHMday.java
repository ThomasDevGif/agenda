package ihm;

import ihm.calendar.IHMcalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IHMday implements ActionListener {

    private JFrame window;
    private String date;
    private JButton buttonBack;

    public IHMday(String day, String month, String year){
        this.date = dateFormat(day, month, year);
        createIHM();
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
        JPanel contentPanel = new JPanel(new GridLayout(11, 0));
        JLabel labelHours = new JLabel("8h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("9h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("10h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("11h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("12h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("13h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("14h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("15h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("16h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("17h");
        contentPanel.add(labelHours);
        labelHours = new JLabel("18h");
        contentPanel.add(labelHours);
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
            new IHMcalendar();
        }
    }
}
