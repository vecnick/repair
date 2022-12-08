package mirea.bd.services;

import mirea.bd.models.Branch;
import mirea.bd.models.Provider;
import mirea.bd.models.Status;
import mirea.bd.repositories.BranchesRepository;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Branch> findAll(boolean sortByRating) {
        if (sortByRating)
            return branchesRepository.findAll(Sort.by("ratingIndicator"));
        else
            return branchesRepository.findAll();
    }

    public List<Branch> findWithPagination(Integer page, Integer branchesPerPage, boolean sortByRating) {
        if (sortByRating)
            return branchesRepository.findAll(PageRequest.of(page, branchesPerPage, Sort.by("ratingIndicator"))).getContent();
        else
            return branchesRepository.findAll(PageRequest.of(page, branchesPerPage)).getContent();
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

    public List<Branch> searchByAddress(String query) {
        return branchesRepository.findByAddressStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
