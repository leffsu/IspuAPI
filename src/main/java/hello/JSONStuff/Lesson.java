package hello.JSONStuff;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lesson {

    static int counterLesson = 0;

    public String getSubject() {
        if (additionalDate == null)
            return subject;
        else
            return subject + " " + additionalDate;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getTime() {
        return time;
    }

    public Map<String, String> getDate() {
        return additionalDate == null ? date : null;
    }

    public ArrayList<Audience> getAudiences() {
        return audiences;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public Lesson(String subject, String type, JsonObject time,
                  JsonArray date, JsonArray audiences,
                  JsonArray teachers) {
        this.subject = subject;
        this.type = type;

        this.time = new HashMap<String, String>();
//        for (int i = 0; i < time.size(); i++) {
        this.time.put(time.get("start").getAsString(),
                time.get("end").getAsString());
//        }
        this.date = new HashMap<String, String>();
        for (int i = 0; i < date.size(); i++) {
            this.date.put(date.get(i).getAsJsonObject().get("start").getAsString(),
                    date.get(i).getAsJsonObject().get("end").getAsString());
        }

        for (int i = 0; i < audiences.size(); i++) {
            this.audiences.add(new Audience(audiences.get(i).getAsJsonObject().get("name").getAsString(),
                    audiences.get(i).getAsJsonObject().get("name").getAsString(),
                    audiences.get(i).getAsJsonObject().get("name").getAsString()));
        }
        for (int i = 0; i < teachers.size(); i++) {
            this.teachers.add(new Teacher(teachers.get(i).getAsJsonObject().get("name").getAsString()));
        }
    }

    public Lesson(String subject, String type, JsonObject time,
                  String additionalDate, JsonArray audiences,
                  JsonArray teachers) {
        this.subject = subject;
        this.type = type;

        this.time = new HashMap<String, String>();
//        for (int i = 0; i < time.size(); i++) {
//            this.time.put(time.get(i).getAsJsonObject().get("start").getAsString(),
//                    time.get(i).getAsJsonObject().get("end").getAsString());
//        }
        this.time.put(time.get("start").getAsString(),
                time.get("end").getAsString());

        this.audiences = new ArrayList<Audience>();
        this.teachers = new ArrayList<Teacher>();

        for (int i = 0; i < audiences.size(); i++) {
            this.audiences.add(new Audience(audiences.get(i).getAsJsonObject().get("name").getAsString(),
                    audiences.get(i).getAsJsonObject().get("name").getAsString(),
                    audiences.get(i).getAsJsonObject().get("name").getAsString()));
        }
        try {
            for (int i = 0; i < teachers.size(); i++) {
                this.teachers.add(new Teacher(teachers.get(i).getAsJsonObject().get("name").getAsString()));
            }
        } catch (IndexOutOfBoundsException e) {
            for (Teacher teacher : this.teachers) {
                System.out.println(teacher.getName());
            }
            e.printStackTrace();
        }

        this.additionalDate = additionalDate;
    }

    String subject;
    String type;

    Map<String, String> time;
    Map<String, String> date;
    ArrayList<Audience> audiences;
    ArrayList<Teacher> teachers;
    String additionalDate;

}