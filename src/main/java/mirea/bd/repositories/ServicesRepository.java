package mirea.bd.repositories;

import mirea.bd.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Service,Integer> {
    List<Service> findByNameStartingWith(String query);
}
