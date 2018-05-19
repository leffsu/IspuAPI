package hello;

import hello.JSONStuff.University;
import hello.Runnables.GetScheduleRunnable;
import hello.Runnables.NewScheduleRunnable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

//    @SuppressWarnings("unused")
//    public static University university;

//    @SuppressWarnings("WeakerAccess")
//    static GetScheduleRunnable getScheduleThread;

    public static String university;

    static NewScheduleRunnable newScheduleRunnable;

    public static void main(String[] args) {


        SpringApplication.run(Main.class, args);

        System.out.println("STARTING UP");

        newScheduleRunnable = new NewScheduleRunnable((long) 14400000);
        newScheduleRunnable.run();

//        getScheduleThread = new GetScheduleRunnable(university, (long) 14400000); // 4hrs
//        getScheduleThread.run();

    }
}