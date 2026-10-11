package com.agrismart.agrismart.repository;
import com.agrismart.agrismart.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    List<Expense> findByFarmId(Long farmId);
    void deleteByFarmId(Long farmId);
}
