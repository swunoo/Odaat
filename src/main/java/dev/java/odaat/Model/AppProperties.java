package dev.java.odaat.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// To make application.properties attributes available to Thymeleaf.
// But didn't work, at least yet.
@Component
public class AppProperties {
    @Value("${app.dir.userimg}")
    String userImgDir;

    public String getUserImgDir(){
        return userImgDir;
    }
}
