package hello.JSONStuff;

import java.io.Serializable;
import java.util.ArrayList;

public class University implements Serializable {

    String name;
    String abbr;

    ArrayList<Faculty> faculties;

    public University(University university) {

    }

    public class Faculty {

        String name;
        ArrayList<Group> groups;

        public class Group {
            String name;
            ArrayList<Lesson> lessons;
        }
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }
}

class Audience {
    String name;
    String addr;
    String lonlat;

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getLonlat() {
        return lonlat;
    }

    public Audience(String name, String addr, String lonlat) {
        this.name = name;
        this.addr = addr;
        this.lonlat = lonlat;
    }
}

class Teacher {
    String name;

    public String getName() {
        return name;
    }

    public Teacher(String name) {
        this.name = name;
    }
}