package dev.java.odaat.Controller.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.java.odaat.Entity.Enums.ProgramType;
import dev.java.odaat.Service.ThemeService;

@Controller
@RequestMapping("/overview")
public class OverviewController {

    @Value("${app.dir.userimg}")
    String userImgDir;
    
    @Autowired
    ThemeService themeService;

    @GetMapping
    public String overview(Model model){
        model.addAttribute("themes", themeService.getAll());
        model.addAttribute("programs", ProgramType.values());
        model.addAttribute("userImgDir", userImgDir);
        return "overview";
    }
}
