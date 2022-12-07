package mirea.bd.repositories;

import mirea.bd.models.RepairMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairMaterialsRepository extends JpaRepository<RepairMaterial,Integer> {
}
