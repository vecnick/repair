package mirea.bd.services;

import mirea.bd.models.Status;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StatusesService {
    private final StatusesRepository statusesRepository;

    public StatusesService(StatusesRepository StatusesRepository) {
        this.statusesRepository = StatusesRepository;
    }

    public List<Status> findAll(){
        return statusesRepository.findAll();
    }

    public Status findOne(int id){
        Optional<Status> foundStatus =  statusesRepository.findById(id);
        return foundStatus.orElse(null);
    }

    @Transactional
    public void save(Status status){
        statusesRepository.save(status);
    }

    @Transactional
    public void update(int id, Status updatedstatus){
        updatedstatus.setId(id);
        statusesRepository.save(updatedstatus);
    }

    @Transactional
    public void delete(int id){
        statusesRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
