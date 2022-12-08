package mirea.bd.repositories;

import mirea.bd.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Client,Integer> {
    List<Client> findByNameStartingWith(String query);
}
