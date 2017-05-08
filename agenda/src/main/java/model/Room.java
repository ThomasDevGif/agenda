package model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("room")
public class Room extends Model {
    public String getLabel() { return getString("label"); }

    public void setLabel(String label) { set("label", label); }

    public int getCapacity() { return getInteger("capacity"); }

    public void setCapacity(int capacity) {
        set("capacity", capacity);
    }
}
