package mirea.bd.repositories;

import mirea.bd.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvidersRepository extends JpaRepository<Provider,Integer> {
    List<Provider> findByNameStartingWith(String query);
}
