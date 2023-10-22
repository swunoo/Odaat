package dev.odaat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("dev.odaat.Mapper")
@ComponentScan(basePackages = "dev.odaat")
public class OdaatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdaatApplication.class, args);
	}

}