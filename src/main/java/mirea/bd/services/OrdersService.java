package mirea.bd.services;

import mirea.bd.models.Order;
import mirea.bd.models.Status;
import mirea.bd.repositories.OrdersRepository;
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

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
