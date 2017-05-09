package ihm.calendar;

import ihm.IHMday;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static ihm.calendar.IHMcalendar.*;

public class TblCalendarRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        // Set background
        if (column == 0 || column == 6){ //Week-end
            setBackground(new Color(224, 224, 224));
        }
        else{ //Week
            setBackground(new Color(255, 255, 255));
        }
        if (value != null){
            if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                setBackground(new Color(130, 177, 255));
            }
        }
        // Set mouse listener
        if (focused && value != null) {
            new IHMday(IHMcalendar.database,
                    IHMcalendar.person,
                    String.valueOf(value),
                    String.valueOf(currentMonth+1),
                    String.valueOf(currentYear));
            IHMcalendar.frmMain.dispose();
        }
        setBorder(null);
        setForeground(Color.black);
        return this;
    }
}
