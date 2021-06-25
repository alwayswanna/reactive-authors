package a.gleb.reactiveauthors.configuration;

import a.gleb.reactiveauthors.dto.AuthorPosts;
import a.gleb.reactiveauthors.service.AuthorPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartLoader implements CommandLineRunner {

    private final AuthorPostService authorPostService;

    @Autowired
    public StartLoader(AuthorPostService authorPostService) {
        this.authorPostService = authorPostService;
    }

    @Override
    public void run(String... args) throws Exception {
        AuthorPosts authorPosts = new AuthorPosts(
                2L, "Some post", "Description", "Bla-Bla-Bla-Bla-Bla", 10L
        );
        log.info("Add to database");
        authorPostService.addAuthorPost(authorPosts);
    }
}
