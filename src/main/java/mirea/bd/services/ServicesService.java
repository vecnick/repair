package mirea.bd.services;


import mirea.bd.models.RepairMaterial;
import mirea.bd.models.Status;
import mirea.bd.repositories.ServicesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<mirea.bd.models.Service> findAll(boolean sortByServicePrice) {
        if (sortByServicePrice)
            return servicesRepository.findAll(Sort.by("retailPrice"));
        else
            return servicesRepository.findAll();
    }

    public List<mirea.bd.models.Service> findWithPagination(Integer page, Integer servicesPerPage, boolean sortByServicePrice) {
        if (sortByServicePrice)
            return servicesRepository.findAll(PageRequest.of(page, servicesPerPage, Sort.by("retailPrice"))).getContent();
        else
            return servicesRepository.findAll(PageRequest.of(page, servicesPerPage)).getContent();
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

    public List<mirea.bd.models.Service> searchByName(String query) {
        return servicesRepository.findByNameStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
