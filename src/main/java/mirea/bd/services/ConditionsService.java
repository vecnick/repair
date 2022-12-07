package mirea.bd.services;

import mirea.bd.models.Condition;
import mirea.bd.models.Status;
import mirea.bd.repositories.ConditionsRepository;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ConditionsService {
    private final ConditionsRepository conditionsRepository;

    public ConditionsService(ConditionsRepository conditionsRepository) {
        this.conditionsRepository = conditionsRepository;
    }

    public List<Condition> findAll(){
        return conditionsRepository.findAll();
    }

    public Condition findOne(int id){
        Optional<Condition> foundCondition =  conditionsRepository.findById(id);
        return foundCondition.orElse(null);
    }

    @Transactional
    public void save(Condition condition){
        conditionsRepository.save(condition);
    }

    @Transactional
    public void update(int id, Condition updatedCondition){
        updatedCondition.setId(id);
        conditionsRepository.save(updatedCondition);
    }

    @Transactional
    public void delete(int id){
        conditionsRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
