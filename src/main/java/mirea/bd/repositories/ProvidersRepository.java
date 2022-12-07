package mirea.bd.repositories;

import mirea.bd.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidersRepository extends JpaRepository<Provider,Integer> {
}
