package hr.foi.airprojekt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

    @Bean
    public Java8TimeDialect java8TimeDialect() {
	    return new Java8TimeDialect();
    }

	@Bean
    public PasswordEncoder providePasswordEncoder() {
	    return new BCryptPasswordEncoder();
    }
}
