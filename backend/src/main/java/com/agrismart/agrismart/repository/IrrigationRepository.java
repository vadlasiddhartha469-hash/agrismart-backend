package com.agrismart.agrismart.repository;
import com.agrismart.agrismart.entity.Irrigation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface IrrigationRepository extends JpaRepository<Irrigation,Long>{
    List<Irrigation> findByCropId(Long cropId);
    void deleteByCropId(Long cropId);
}
