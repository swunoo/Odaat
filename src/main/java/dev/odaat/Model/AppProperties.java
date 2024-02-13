package dev.odaat.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Value("${app.dir.userimg}")
    String userImgDir;

    public String getUserImgDir(){
        return userImgDir;
    }
}
