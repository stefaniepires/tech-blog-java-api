package techblog.techblogjavaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity //marks as a persistable object so User class can map to a table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //properties to be ignored
@Table(name = "user")
public class User {
    @Id //unique identifier
    @GeneratedValue(strategy = GenerationType.AUTO) //number should be auto generated
    private Integer id;
    @Column(unique = true) //unique usernames only
    private String username;
    @Column(unique = true) //unique emails only
    private String email;
    private String password;
    @Transient //tells Spring Data API that data shouldnt be persisted
    boolean loggedIn;

    //TABLE RELATIONSHIPS
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    // Need to use FetchType.LAZY to resolve multiple bags exception
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;


    //GETTERS AND SETTERS

    public User() {
    }

    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isLoggedIn() == user.isLoggedIn() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getPosts(), user.getPosts()) &&
                Objects.equals(getComments(), user.getComments());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), isLoggedIn(), getPosts(), getComments());
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                ", posts=" + posts +
                ", comments=" + comments +
                '}';
    }
}
