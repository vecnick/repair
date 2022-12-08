package mirea.bd.services;

import mirea.bd.models.Order;
import mirea.bd.models.RepairMaterial;
import mirea.bd.repositories.RepairMaterialsRepository;
import mirea.bd.repositories.ServicesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RepairMaterialsService {

    private final RepairMaterialsRepository repairMaterialsRepository;

    public RepairMaterialsService(RepairMaterialsRepository repairMaterialsRepository) {
        this.repairMaterialsRepository = repairMaterialsRepository;
    }

    public List<RepairMaterial> findAll(){
        return repairMaterialsRepository.findAll();
    }

    public List<RepairMaterial> findAll(boolean sortByRetailPrice) {
        if (sortByRetailPrice)
            return repairMaterialsRepository.findAll(Sort.by("retailPrice"));
        else
            return repairMaterialsRepository.findAll();
    }

    public List<RepairMaterial> findWithPagination(Integer page, Integer repairMaterialsPerPage, boolean sortByRetailPrice) {
        if (sortByRetailPrice)
            return repairMaterialsRepository.findAll(PageRequest.of(page, repairMaterialsPerPage, Sort.by("retailPrice"))).getContent();
        else
            return repairMaterialsRepository.findAll(PageRequest.of(page, repairMaterialsPerPage)).getContent();
    }

    public RepairMaterial findOne(int id){
        Optional<RepairMaterial> foundRepairMaterial =  repairMaterialsRepository.findById(id);
        return foundRepairMaterial.orElse(null);
    }

    @Transactional
    public void save(RepairMaterial repairMaterial){
        repairMaterialsRepository.save(repairMaterial);
    }

    @Transactional
    public void update(int id, RepairMaterial updatedMaterial){
        updatedMaterial.setId(id);
        repairMaterialsRepository.save(updatedMaterial);
    }

    @Transactional
    public void delete(int id){
        repairMaterialsRepository.deleteById(id);
    }

    public List<RepairMaterial> searchByName(String query) {
        return repairMaterialsRepository.findByNameStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
