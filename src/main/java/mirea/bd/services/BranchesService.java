package mirea.bd.services;

import mirea.bd.models.Branch;
import mirea.bd.models.Status;
import mirea.bd.repositories.BranchesRepository;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BranchesService {
    private final BranchesRepository branchesRepository;

    public BranchesService(BranchesRepository branchesRepository) {
        this.branchesRepository = branchesRepository;
    }

    public List<Branch> findAll(){
        return branchesRepository.findAll();
    }

    public Branch findOne(int id){
        Optional<Branch> foundBranch =  branchesRepository.findById(id);
        return foundBranch.orElse(null);
    }

    @Transactional
    public void save(Branch branch){
        branchesRepository.save(branch);
    }

    @Transactional
    public void update(int id, Branch updatedBranch){
        updatedBranch.setId(id);
        branchesRepository.save(updatedBranch);
    }

    @Transactional
    public void delete(int id){
        branchesRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
