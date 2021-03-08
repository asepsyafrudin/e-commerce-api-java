package co.g2academy.StoreFront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication (scanBasePackages = {"co.g2academy.StoreFront"})
@EnableCaching
public class StoreFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreFrontApplication.class, args);
	}
}
