package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.dto.ExpenseRequest;
import com.agrismart.agrismart.entity.*;
import com.agrismart.agrismart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/expenses") @CrossOrigin(origins="*")
public class ExpenseController {
 private final ExpenseRepository repo; private final FarmRepository farms;
 public ExpenseController(ExpenseRepository repo,FarmRepository farms){this.repo=repo;this.farms=farms;}
 @PostMapping public Expense add(@RequestBody ExpenseRequest r){Expense e=new Expense();map(e,r);e.setFarm(farms.findById(r.farmId).orElseThrow());return repo.save(e);}
 @GetMapping("/farm/{farmId}") public List<Expense> all(@PathVariable Long farmId){return repo.findByFarmId(farmId);}
 @PutMapping("/{id}") public Expense update(@PathVariable Long id,@RequestBody ExpenseRequest r){Expense e=repo.findById(id).orElseThrow();map(e,r);if(r.farmId!=null)e.setFarm(farms.findById(r.farmId).orElseThrow());return repo.save(e);}
 @DeleteMapping("/{id}") public Map<String,String> delete(@PathVariable Long id){repo.deleteById(id);return Map.of("message","Expense deleted");}
 private void map(Expense e,ExpenseRequest r){e.setCategory(r.category);e.setAmount(r.amount);e.setExpenseDate(r.expenseDate);}
}
