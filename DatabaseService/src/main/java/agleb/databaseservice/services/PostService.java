package agleb.databaseservice.services;

import agleb.databaseservice.exceptions.NoSuchPostException;
import agleb.databaseservice.model.Post;
import agleb.databaseservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return StreamSupport
                .stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Post getPostById(Long id) {
        Post post = postRepository.findPostById(id);
        if (post == null) {
            throw new NoSuchPostException("NoSuchPostException: post with [id]: " + id + " does not exist in database");
        } else {
            return post;
        }
    }

    public List<Post> getPostByTitle(String title) {
        List<Post> post = postRepository.findPostByTitle(title);
        if (post == null) {
            throw new NoSuchPostException("NoSuchPostException: posts with [title]: " + title + " does not exist in database");
        } else {
            return post;
        }
    }

    @Transactional
    public Post editSelectedPost(Long id, Post post) {
        Post toEditPost = getPostById(id);
        if (toEditPost == null) {
            throw new NoSuchPostException("NoSuchPostException: post with [id]: " + id + " does not exist in database");
        } else {
            toEditPost.setTitle(post.getTitle());
            toEditPost.setDescription(post.getDescription());
            toEditPost.setFull_story(post.getFull_story());
            toEditPost.setLikes(post.getLikes());
            toEditPost.setAuthor(post.getAuthor());
            return postRepository.save(toEditPost);
        }
    }

    public Post removePostById(Long id) {
        Post post = getPostById(id);
        if (post == null) {
            throw new NoSuchPostException("NoSuchPostException: post with [id]: " + id + " does not exist in database");
        } else {
            postRepository.delete(post);
            return post;
        }
    }
}
