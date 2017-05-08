package model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("person")
public class Person extends Model {
    public String getLogin() {
        return getString("login");
    }

    public void setLogin(String login) {
        set("login", login);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        set("password", password);
    }

    public String getFirstname() {
        return getString("firstname");
    }

    public void setFirstname(String firstname) {
        set("firstname", firstname);
    }

    public String getLastname() {
        return getString("lastname");
    }

    public void setLastname(String lastname) {
        set("lastname", lastname);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public int getClassId() { return getInteger("classId"); }

    public void setClassId(int classId) {
        set("classId", classId);
    }

    public int getType() { return getInteger("type"); }

    public void setType(int type) {
        set("type", type);
    }
}