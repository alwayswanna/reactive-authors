package agleb.databaseservice.repositories;

import agleb.databaseservice.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    
    Post findPostById(Long id);

    List<Post> findPostByTitle(String title);
}
