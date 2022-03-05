package techblog.techblogjavaapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techblog.techblogjavaapi.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllPostsByUserId(Integer id) throws Exception;
}
