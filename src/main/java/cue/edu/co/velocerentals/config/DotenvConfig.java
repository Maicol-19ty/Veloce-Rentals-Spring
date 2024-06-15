package cue.edu.co.velocerentals.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean // Defines a bean managed by the Spring container.
    public Dotenv getDotenv() {
        // Loads the environment variables from the .env file.
        return Dotenv.load();
    }
}
