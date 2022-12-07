package mirea.bd.services;


import mirea.bd.repositories.ServicesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServicesService {
    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<mirea.bd.models.Service> findAll(){
        return servicesRepository.findAll();
    }

    public mirea.bd.models.Service findOne(int id){
        Optional<mirea.bd.models.Service> foundStatus =  servicesRepository.findById(id);
        return foundStatus.orElse(null);
    }

    @Transactional
    public void save(mirea.bd.models.Service service){
        servicesRepository.save(service);
    }

    @Transactional
    public void update(int id, mirea.bd.models.Service updatedService){
        updatedService.setId(id);
        servicesRepository.save(updatedService);
    }

    @Transactional
    public void delete(int id){
        servicesRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
