package mirea.bd.repositories;

import mirea.bd.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer> {
    List<Order> findByNameStartingWith(String query);
}
