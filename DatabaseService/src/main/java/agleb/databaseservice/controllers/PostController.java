package agleb.databaseservice.controllers;

import agleb.databaseservice.model.Post;
import agleb.databaseservice.model.dto.PostDTO;
import agleb.databaseservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/db_service")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPost(@RequestBody final PostDTO postDTO){
        Post post = postService.createPost(Post.from(postDTO));
        return new ResponseEntity<>(PostDTO.from(post), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable final Long id){
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(PostDTO.from(post), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<Post> postList = postService.getAllPosts();
        List<PostDTO> postDTOList = postList
                .stream()
                .map(PostDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDTO> deletePostById(@PathVariable final Long id){
        Post postDeleted = postService.removePostById(id);
        return new ResponseEntity<>(PostDTO.from(postDeleted), HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDTO> editPostById(@PathVariable final Long id,
                                            @RequestBody final PostDTO postDTO){
        Post post = postService.editSelectedPost(id, Post.from(postDTO));
        return new ResponseEntity<>(PostDTO.from(post), HttpStatus.OK);
    }


}
