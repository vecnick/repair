package mirea.bd.services;

import mirea.bd.models.Post;
import mirea.bd.repositories.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<Post> findAll(){
        return postsRepository.findAll();
    }

    public Post findOne(int id){
        Optional<Post> foundPost =  postsRepository.findById(id);
        return foundPost.orElse(null);
    }

    @Transactional
    public void save(Post post){
        postsRepository.save(post);
    }

    @Transactional
    public void update(int id, Post updatedPost){
        updatedPost.setId(id);
        postsRepository.save(updatedPost);
    }

    @Transactional
    public void delete(int id){
        postsRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
