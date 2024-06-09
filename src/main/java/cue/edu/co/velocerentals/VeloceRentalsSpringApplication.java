package cue.edu.co.velocerentals;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class VeloceRentalsSpringApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("DB_USER", Objects.requireNonNull(dotenv.get("DB_USER")));
        System.setProperty("DB_PASS", Objects.requireNonNull(dotenv.get("DB_PASS")));

        SpringApplication.run(VeloceRentalsSpringApplication.class, args);
    }

}
