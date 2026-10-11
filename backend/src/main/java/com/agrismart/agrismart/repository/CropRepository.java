package com.agrismart.agrismart.repository;
import com.agrismart.agrismart.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CropRepository extends JpaRepository<Crop,Long>{
    List<Crop> findByFarmId(Long farmId);
    void deleteByFarmId(Long farmId);
}
