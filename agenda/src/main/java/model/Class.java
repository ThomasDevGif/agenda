package model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("class")
public class Class extends Model {
    public String getLabel() { return getString("label"); }

    public void setLabel(String label) { set("label", label); }
}
