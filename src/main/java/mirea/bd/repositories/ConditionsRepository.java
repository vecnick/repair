package mirea.bd.repositories;

import mirea.bd.models.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionsRepository extends JpaRepository<Condition,Integer> {
    List<Condition> findByDescriptionStartingWith(String query);
}
