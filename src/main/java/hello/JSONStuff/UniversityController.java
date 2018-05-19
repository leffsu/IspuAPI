package hello.JSONStuff;

import hello.Main;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityController {

    @RequestMapping("/university")
    public String string(@RequestParam(value="name", defaultValue="World") String name) {
        return Main.university;
    }
}