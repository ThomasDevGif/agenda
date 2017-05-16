package model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("course")
public class Course extends Model {

    public int getPersonId() { return getInteger("personId"); }

    public void setPersonId(int personId) {
        set("personId", personId);
    }

    public int getClassId() { return getInteger("classId"); }

    public void setClassId(int classId) {
        set("classId", classId);
    }

    public int getRoomId() { return getInteger("roomId"); }

    public void setRoomId(int roomId) {
        set("roomId", roomId);
    }

    public int getSubjectId() { return getInteger("subjectId"); }

    public void setSubjectId(int subjectId) {
        set("subjectId", subjectId);
    }

    public String getDate() { return getString("date"); }

    public void setDate(String date) {
        set("date", date);
    }

    public int getHour() { return getInteger("hour"); }

    public void setHour(int hour) {
        set("hour", hour);
    }

}
