package mirea.bd.services;

import mirea.bd.models.Branch;
import mirea.bd.models.Client;
import mirea.bd.models.Provider;
import mirea.bd.models.Status;
import mirea.bd.repositories.ClientsRepository;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientsService {
    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Client> findAll(){
        return clientsRepository.findAll();
    }

    public List<Client> findAll(boolean sortBySurname) {
        if (sortBySurname)
            return clientsRepository.findAll(Sort.by("secondName"));
        else
            return clientsRepository.findAll();
    }

    public List<Client> findWithPagination(Integer page, Integer clientsPerPage, boolean sortBySurname) {
        if (sortBySurname)
            return clientsRepository.findAll(PageRequest.of(page, clientsPerPage, Sort.by("secondName"))).getContent();
        else
            return clientsRepository.findAll(PageRequest.of(page, clientsPerPage)).getContent();
    }
    public Client findOne(int id){
        Optional<Client> foundClient =  clientsRepository.findById(id);
        return foundClient.orElse(null);
    }

    @Transactional
    public void save(Client client){
        clientsRepository.save(client);
    }

    @Transactional
    public void update(int id, Client updatedClient){
        updatedClient.setId(id);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void delete(int id){
        clientsRepository.deleteById(id);
    }

    public List<Client> searchByName(String query) {
        return clientsRepository.findByNameStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
