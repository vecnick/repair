package mirea.bd.repositories;

import mirea.bd.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByNameStartingWith(String query);
}
