package dev.java.odaat.Controller.App;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// For testing, redirecting.
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(){
        return "redirect:/dashboard";
    }
}
