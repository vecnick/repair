package mirea.bd.repositories;

import mirea.bd.models.RepairMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairMaterialsRepository extends JpaRepository<RepairMaterial,Integer> {
    List<RepairMaterial> findByNameStartingWith(String query);
}
