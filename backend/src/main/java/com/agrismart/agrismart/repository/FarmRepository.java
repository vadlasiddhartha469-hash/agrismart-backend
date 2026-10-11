package com.agrismart.agrismart.repository;
import com.agrismart.agrismart.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FarmRepository extends JpaRepository<Farm,Long>{
    List<Farm> findByUserId(Long userId);
}
