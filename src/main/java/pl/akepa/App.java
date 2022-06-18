package pl.akepa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.akepa.creditcard.NameProvider;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    NameProvider createNameProvider() {
        return new NameProvider();
    }
}
