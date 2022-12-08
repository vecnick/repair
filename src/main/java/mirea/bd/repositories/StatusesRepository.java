package mirea.bd.repositories;

import mirea.bd.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusesRepository extends JpaRepository<Status,Integer> {
    List<Status> findByNameStartingWith(String query);
}