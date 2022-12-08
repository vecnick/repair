package mirea.bd.repositories;

import mirea.bd.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post,Integer> {
    List<Post> findByNameStartingWith(String query);
}
