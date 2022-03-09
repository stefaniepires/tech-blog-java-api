package techblog.techblogjavaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import techblog.techblogjavaapi.model.Post;
import techblog.techblogjavaapi.model.User;
import techblog.techblogjavaapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

@RestController
public class UserController {
    @Autowired //tells Spring to scan the project of
    // objects that will need to be instantiated for a class or method to run
    UserRepository repository;

@GetMapping("/api/users")
public List<User> getAllUsers() {
    //List<User> bc want to return a list of users
    List<User> userList = repository.findAll();
    //calling getPosts() function for every user that is assigned to variable u inside of userList
    for (User u : userList) {
        List<Post> postList = u.getPosts();
        }
    return userList;
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        User returnUser = repository.getOne(id);
        List<Post> postList = returnUser.getPosts();

        return returnUser;
    }

    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) {
        // Encrypt password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        repository.save(user);
        return user;
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User tempUser = repository.getOne(id);
        if (!tempUser.equals(null)) {
            user.setId(tempUser.getId());
            repository.save(user);
        }
        return user;
    }
    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}

