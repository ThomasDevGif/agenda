package ihm.calendar;

import data.Database;
import model.Person;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class IHMcalendar {

    // Variables
    static Database database;
    static Person person;
    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext;
    static JTable tblCalendar;
    static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    static JPanel pnlCalendar; //The panel
    static int realDay, realMonth, realYear, currentMonth, currentYear;

    public IHMcalendar(Database database, Person person){
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {System.out.println("ERROR: "+e.getMessage());}
        catch (InstantiationException e) {System.out.println("ERROR: "+e.getMessage());}
        catch (IllegalAccessException e) {System.out.println("ERROR: "+e.getMessage());}
        catch (UnsupportedLookAndFeelException e) {System.out.println("ERROR: "+e.getMessage());}

        this.database = database;
        this.person = person;
        createComponents();
        refreshCalendar (realMonth, realYear); //Refresh ihm.calendar
        initializeListeners();
    }

    public static void createComponents(){
        // Instantiate components
        frmMain = new JFrame("Calendrier application");
        lblMonth = new JLabel ("Janvier");
        lblYear = new JLabel ("Année:");
        cmbYear = new JComboBox();
        btnPrev = new JButton ("<<");
        btnNext = new JButton (">>");
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar); //Table using the above model
        stblCalendar = new JScrollPane(tblCalendar); //The scrollpane of the above table
        pnlCalendar = new JPanel(null); //Create the "panel" to place components

        // Preparing the frame
        frmMain.setSize(330, 380); //Two arguments: width and height
        pane = frmMain.getContentPane();
        pane.setLayout(null); //Apply the null layout
        frmMain.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);

        // Setting the border of panel
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendrier")); //Set border

        // Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(stblCalendar);

        // Set bounds
        pnlCalendar.setBounds(0, 0, 320, 350);
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
        lblYear.setBounds(10, 320, 80, 20);
        cmbYear.setBounds(230, 320, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(260, 25, 50, 25);
        stblCalendar.setBounds(10, 50, 300, 259);

        // Make the frame visible
        frmMain.setResizable(false);
        frmMain.setVisible(true);

        // Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create ihm.calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

        // Populate combo box
        for (int i=realYear-100; i<=realYear+100; i++){
            cmbYear.addItem(String.valueOf(i));
        }

        // Preparing the ihm.calendar
        String[] headers = {"Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"}; //All headers
        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }
        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background

        // No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);

        // Single cell selection
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set row/column count
        tblCalendar.setRowHeight(38);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);
    }

    public static void refreshCalendar(int month, int year){
        String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        int nod, som; //Number Of Days, Start Of Month

        // Prepare buttons
        btnPrev.setEnabled(true); //Enable buttons at first
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with ihm.calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        // Get number of days and start of month
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        // Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }

        for (int i=1; i<=nod; i++){
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }

        //Apply renderer
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new TblCalendarRenderer());
    }

    public static void initializeListeners(){
        //Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());
    }

    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Foward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Foward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b); //Get the numeric value
                refreshCalendar(currentMonth, currentYear); //Refresh
            }
        }
    }

}
