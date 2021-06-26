package a.gleb.reactiveauthors;

import a.gleb.reactiveauthors.routes.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveAuthorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveAuthorsApplication.class, args);
    }

}
