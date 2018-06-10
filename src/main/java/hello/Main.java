package hello;

import hello.JSONStuff.University;
import hello.Runnables.GetScheduleRunnable;
import hello.Runnables.NewScheduleRunnable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static String university;

    static NewScheduleRunnable newScheduleRunnable;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("STARTING UP");
        newScheduleRunnable = new NewScheduleRunnable((long) 14400000);
        newScheduleRunnable.run();
    }
}