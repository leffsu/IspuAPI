package hello.Runnables;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hello.JSONStuff.CustomDateDeserializer;
import hello.JSONStuff.Lesson;
import hello.JSONStuff.University;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class GetScheduleRunnable implements Runnable {

    public GetScheduleRunnable(University university, long timeToWait){
        this.savedUniversity = university;
        this.timeToWait = timeToWait;
    }

    University savedUniversity;
    University university;
    private long timeToWait=0;
    private static final String USER_AGENT = "Mozilla/5.0";

    private void getSchedule(){
        university = null;
        String requestURL = "http://schedule.ispu.ru/APInew/schedule/get_schedule?test=1";

        URL obj = null;
        HttpURLConnection con = null;
//        int responseCode = 0;
        try {
            obj = new URL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            if (obj != null) {
                con = (HttpURLConnection) obj.openConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (con != null) {
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
//                responseCode = con.getResponseCode();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        StringBuffer response = null;
        try {
            BufferedReader in = null;
            if (con != null) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            response = new StringBuffer();

            if (in != null) {
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Lesson.class, new CustomDateDeserializer());

        Gson gson = gsonBuilder.create();

        if (response != null) {
            university = gson.fromJson(response.toString(), University.class);
        }
    }

    public void run() {
        while (true) {
            getSchedule();
            if (university != null) {
                savedUniversity = university;
                System.out.println("SCHEDULE RECEIVED & PARSED");
                try {
                    sleep(timeToWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
