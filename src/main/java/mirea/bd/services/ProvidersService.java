package mirea.bd.services;

import mirea.bd.models.Provider;
import mirea.bd.models.Provider;
import mirea.bd.repositories.ProvidersRepository;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProvidersService {
    private final ProvidersRepository providersRepository;

    public ProvidersService(ProvidersRepository providersRepository) {
        this.providersRepository = providersRepository;
    }

    public List<Provider> findAll(){
        return providersRepository.findAll();
    }


    public Provider findOne(int id){
        Optional<Provider> foundProvider =  providersRepository.findById(id);
        return foundProvider.orElse(null);
    }

    @Transactional
    public void save(Provider provider){
        providersRepository.save(provider);
    }

    @Transactional
    public void update(int id, Provider updatedProvider){
        updatedProvider.setId(id);
        providersRepository.save(updatedProvider);
    }

    @Transactional
    public void delete(int id){
        providersRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
