package hello.Runnables;

import hello.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static java.lang.Thread.sleep;

@SuppressWarnings("Duplicates")
public class NewScheduleRunnable implements Runnable {

    public NewScheduleRunnable(long timeToWait){
        this.timeToWait = timeToWait;
    }

    private String university;

    private long timeToWait=0;
    private static final String USER_AGENT = "Mozilla/5.0";

    private void getSchedule(){
        university = null;
        String requestURL = "http://schedule.ispu.ru/APInew/schedule/get_schedule?test=1";

        URL obj = null;
        HttpURLConnection con = null;

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


        university = response.toString();
    }

    public void run() {
        while (true) {
            getSchedule();
            if (university != null) {
                Main.university = university;
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
