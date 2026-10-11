package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.dto.FertilizerRequest;
import com.agrismart.agrismart.entity.*;
import com.agrismart.agrismart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/fertilizers") @CrossOrigin(origins="*")
public class FertilizerController {
 private final FertilizerRepository repo; private final CropRepository crops;
 public FertilizerController(FertilizerRepository repo,CropRepository crops){this.repo=repo;this.crops=crops;}
 @PostMapping public Fertilizer add(@RequestBody FertilizerRequest r){Fertilizer f=new Fertilizer();map(f,r);f.setCrop(crops.findById(r.cropId).orElseThrow());return repo.save(f);}
 @GetMapping("/crop/{cropId}") public List<Fertilizer> all(@PathVariable Long cropId){return repo.findByCropId(cropId);}
 @PutMapping("/{id}") public Fertilizer update(@PathVariable Long id,@RequestBody FertilizerRequest r){Fertilizer f=repo.findById(id).orElseThrow();map(f,r);if(r.cropId!=null)f.setCrop(crops.findById(r.cropId).orElseThrow());return repo.save(f);}
 @DeleteMapping("/{id}") public Map<String,String> delete(@PathVariable Long id){repo.deleteById(id);return Map.of("message","Fertilizer record deleted");}
 private void map(Fertilizer f,FertilizerRequest r){f.setFertilizerName(r.fertilizerName);f.setQuantity(r.quantity);f.setApplicationDate(r.applicationDate);}
}
