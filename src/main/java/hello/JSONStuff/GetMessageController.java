package hello.JSONStuff;

import hello.Main;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class GetMessageController {

    @RequestMapping("/getMessages")
    public String string(@RequestParam(value="name", defaultValue="World") String name) {
        String line = "hm";
        StringBuilder saved = new StringBuilder();
        BufferedReader writer = null;
        try {
            writer = new BufferedReader(new FileReader("savedMessages"));
            while (line!=null){
                saved.append(line + "\n");
                line = writer.readLine();
            }

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
        System.out.println(saved);
        return saved.toString();
    }
}