package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.dto.FarmRequest;
import com.agrismart.agrismart.entity.*;
import com.agrismart.agrismart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/farms") @CrossOrigin(origins="*")
public class FarmController {
 private final FarmRepository farms; private final UserRepository users; private final CropRepository crops; private final ExpenseRepository expenses; private final FertilizerRepository fertilizers; private final IrrigationRepository irrigation;
 public FarmController(FarmRepository farms,UserRepository users,CropRepository crops,ExpenseRepository expenses,FertilizerRepository fertilizers,IrrigationRepository irrigation){this.farms=farms;this.users=users;this.crops=crops;this.expenses=expenses;this.fertilizers=fertilizers;this.irrigation=irrigation;}
 @PostMapping("/{userId}") public Farm add(@PathVariable Long userId,@RequestBody FarmRequest r){
  Farm f=new Farm(); map(f,r); f.setUser(users.findById(userId).orElseThrow()); return farms.save(f);
 }
 @GetMapping("/{userId}") public List<Farm> all(@PathVariable Long userId){return farms.findByUserId(userId);}
 @GetMapping("/one/{id}") public Farm one(@PathVariable Long id){return farms.findById(id).orElseThrow();}
 @PutMapping("/one/{id}") public Farm update(@PathVariable Long id,@RequestBody FarmRequest r){Farm f=farms.findById(id).orElseThrow();map(f,r);return farms.save(f);}
 @DeleteMapping("/one/{id}") public Map<String,String> delete(@PathVariable Long id){
   Farm f=farms.findById(id).orElseThrow(); List<Crop> cs=crops.findByFarmId(id);
   for(Crop c:cs){fertilizers.deleteByCropId(c.getId()); irrigation.deleteByCropId(c.getId());}
   crops.deleteByFarmId(id); expenses.deleteByFarmId(id); farms.delete(f); return Map.of("message","Farm deleted");
 }
 private void map(Farm f,FarmRequest r){f.setFarmName(r.farmName);f.setLocation(r.location);f.setArea(r.area);f.setSoilType(r.soilType);f.setWaterAvailability(r.waterAvailability);}
}
