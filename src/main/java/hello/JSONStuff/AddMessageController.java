package hello.JSONStuff;

import hello.Main;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class AddMessageController {

    @RequestMapping("/addMessage/{author}/{text}")
    public String string(
            @PathVariable(value = "author") String author,
            @PathVariable(value = "text") String text) {

        System.out.println(author + " " + text);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("savedMessages", true));
            writer.write(author + "/" + text+"\n");

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
        return "ok";
    }
}