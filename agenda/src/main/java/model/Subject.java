package model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("subject")
public class Subject extends Model {
    public String getLabel() { return getString("label"); }

    public void setLabel(String label) { set("label", label); }

    public int getRed() { return getInteger("red"); }

    public void setRed(int red) { set("red", red); }

    public int getGreen() { return getInteger("green"); }

    public void setGreen(int green) { set("green", green); }

    public int getBlue() { return getInteger("blue"); }

    public void setBlue(int blue) { set("blue", blue); }
}
