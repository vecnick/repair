package mirea.bd.repositories;

import mirea.bd.models.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionsRepository extends JpaRepository<Condition,Integer> {
}
