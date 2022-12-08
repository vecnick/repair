package mirea.bd.services;

import mirea.bd.models.Client;
import mirea.bd.models.Order;
import mirea.bd.models.Provider;
import mirea.bd.models.Status;
import mirea.bd.repositories.OrdersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public List<Order> findAll(boolean sortByTotalPrice) {
        if (sortByTotalPrice)
            return ordersRepository.findAll(Sort.by("totalPrice"));
        else
            return ordersRepository.findAll();
    }

    public List<Order> findWithPagination(Integer page, Integer ordersPerPage, boolean sortByTotalPrice) {
        if (sortByTotalPrice)
            return ordersRepository.findAll(PageRequest.of(page, ordersPerPage, Sort.by("totalPrice"))).getContent();
        else
            return ordersRepository.findAll(PageRequest.of(page, ordersPerPage)).getContent();
    }

    public Order findOne(int id){
        Optional<Order> foundOrder =  ordersRepository.findById(id);
        return foundOrder.orElse(null);
    }

    @Transactional
    public void save(Order order){
        ordersRepository.save(order);
    }

    @Transactional
    public void update(int id, Order updatedOrder){
        updatedOrder.setId(id);
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id){
        ordersRepository.deleteById(id);
    }

    public List<Order> searchByName(String query) {
        return ordersRepository.findByNameStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
