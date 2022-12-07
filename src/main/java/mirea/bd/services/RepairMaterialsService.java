package mirea.bd.services;

import mirea.bd.models.RepairMaterial;
import mirea.bd.models.Service;
import mirea.bd.repositories.RepairMaterialsRepository;
import mirea.bd.repositories.ServicesRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class RepairMaterialsService {

    private final RepairMaterialsRepository repairMaterialsRepository;

    public RepairMaterialsService(RepairMaterialsRepository repairMaterialsRepository) {
        this.repairMaterialsRepository = repairMaterialsRepository;
    }

    public List<RepairMaterial> findAll(){
        return repairMaterialsRepository.findAll();
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

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
