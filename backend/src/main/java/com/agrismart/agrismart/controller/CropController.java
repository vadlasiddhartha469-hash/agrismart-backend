package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.dto.CropRequest;
import com.agrismart.agrismart.entity.*;
import com.agrismart.agrismart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/crops") @CrossOrigin(origins="*")
public class CropController {
 private final CropRepository crops; private final FarmRepository farms; private final FertilizerRepository fertilizers; private final IrrigationRepository irrigation;
 public CropController(CropRepository crops,FarmRepository farms,FertilizerRepository fertilizers,IrrigationRepository irrigation){this.crops=crops;this.farms=farms;this.fertilizers=fertilizers;this.irrigation=irrigation;}
 @PostMapping public Crop add(@RequestBody CropRequest r){Crop c=new Crop();map(c,r);c.setFarm(farms.findById(r.farmId).orElseThrow());return crops.save(c);}
 @GetMapping("/farm/{farmId}") public List<Crop> all(@PathVariable Long farmId){return crops.findByFarmId(farmId);}
 @PutMapping("/{id}") public Crop update(@PathVariable Long id,@RequestBody CropRequest r){Crop c=crops.findById(id).orElseThrow();map(c,r);if(r.farmId!=null)c.setFarm(farms.findById(r.farmId).orElseThrow());return crops.save(c);}
 @DeleteMapping("/{id}") public Map<String,String> delete(@PathVariable Long id){crops.findById(id).orElseThrow();fertilizers.deleteByCropId(id);irrigation.deleteByCropId(id);crops.deleteById(id);return Map.of("message","Crop deleted");}
 private void map(Crop c,CropRequest r){c.setCropName(r.cropName);c.setStatus(r.status);c.setSowingDate(r.sowingDate);c.setExpectedHarvestDate(r.expectedHarvestDate);c.setExpectedYield(r.expectedYield);c.setExpectedRevenue(r.expectedRevenue);}
}
