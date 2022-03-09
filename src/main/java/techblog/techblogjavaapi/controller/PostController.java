package techblog.techblogjavaapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import techblog.techblogjavaapi.model.Post;
import techblog.techblogjavaapi.repository.PostRepository;
import techblog.techblogjavaapi.repository.UserRepository;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        List<Post> postList = repository.findAll();

        return postList;
    }
    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable Integer id) {
        Post returnPost = repository.getOne(id);

        return returnPost;
    }

    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody Post post) {
        repository.save(post);
        return post;
    }
    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        Post tempPost = repository.getOne(id);
        tempPost.setTitle(post.getTitle());
        return repository.save(tempPost);
    }

    @DeleteMapping("/api/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        repository.deleteById(id);
    }
}
