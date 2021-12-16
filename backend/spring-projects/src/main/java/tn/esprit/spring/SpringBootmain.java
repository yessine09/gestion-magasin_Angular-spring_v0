package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tn.esprit.spring.batch")
public class SpringBootmain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootmain.class, args);
	}
}
