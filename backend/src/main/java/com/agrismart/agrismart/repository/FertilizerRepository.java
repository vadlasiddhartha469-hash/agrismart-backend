package com.agrismart.agrismart.repository;
import com.agrismart.agrismart.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FertilizerRepository extends JpaRepository<Fertilizer,Long>{
    List<Fertilizer> findByCropId(Long cropId);
    void deleteByCropId(Long cropId);
}
