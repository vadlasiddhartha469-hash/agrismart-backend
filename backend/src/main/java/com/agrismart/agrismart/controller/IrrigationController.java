package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.dto.IrrigationRequest;
import com.agrismart.agrismart.entity.*;
import com.agrismart.agrismart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/irrigation") @CrossOrigin(origins="*")
public class IrrigationController {
 private final IrrigationRepository repo; private final CropRepository crops;
 public IrrigationController(IrrigationRepository repo,CropRepository crops){this.repo=repo;this.crops=crops;}
 @PostMapping public Irrigation add(@RequestBody IrrigationRequest r){Irrigation i=new Irrigation();map(i,r);i.setCrop(crops.findById(r.cropId).orElseThrow());return repo.save(i);}
 @GetMapping("/crop/{cropId}") public List<Irrigation> all(@PathVariable Long cropId){return repo.findByCropId(cropId);}
 @PutMapping("/{id}") public Irrigation update(@PathVariable Long id,@RequestBody IrrigationRequest r){Irrigation i=repo.findById(id).orElseThrow();map(i,r);if(r.cropId!=null)i.setCrop(crops.findById(r.cropId).orElseThrow());return repo.save(i);}
 @DeleteMapping("/{id}") public Map<String,String> delete(@PathVariable Long id){repo.deleteById(id);return Map.of("message","Irrigation record deleted");}
 private void map(Irrigation i,IrrigationRequest r){i.setIrrigationDate(r.irrigationDate);i.setWaterQuantity(r.waterQuantity);i.setMethod(r.method);}
}
