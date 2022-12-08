package mirea.bd.services;

import mirea.bd.models.Order;
import mirea.bd.models.Post;
import mirea.bd.models.Provider;
import mirea.bd.repositories.PostsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Post> findWithPagination(Integer page, Integer postsPerPage) {
            return postsRepository.findAll(PageRequest.of(page, postsPerPage)).getContent();
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

    public List<Post> searchByName(String query) {
        return postsRepository.findByNameStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
