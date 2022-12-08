package mirea.bd.repositories;

import mirea.bd.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchesRepository extends JpaRepository<Branch,Integer> {
    List<Branch> findByAddressStartingWith(String query);
}