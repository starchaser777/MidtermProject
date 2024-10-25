package kr.ac.kopo.midtermproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MidtermProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidtermProjectApplication.class, args);
    }

}
