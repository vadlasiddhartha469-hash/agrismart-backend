package com.agrismart.agrismart.controller;
import com.agrismart.agrismart.entity.*;
import com.agrismart.agrismart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/agri") @CrossOrigin(origins="*")
public class AgriController {
 private final FarmRepository farms; private final CropRepository crops; private final ExpenseRepository expenses; private final IrrigationRepository irrigation;
 public AgriController(FarmRepository farms,CropRepository crops,ExpenseRepository expenses,IrrigationRepository irrigation){this.farms=farms;this.crops=crops;this.expenses=expenses;this.irrigation=irrigation;}

 @GetMapping("/recommendation")
 public Map<String,Object> recommend(@RequestParam String soilType,@RequestParam String waterAvailability,@RequestParam String season){
   String crop="Maize", reason="Balanced option for the supplied conditions.";
   String s=soilType.toLowerCase(), w=waterAvailability.toLowerCase(), se=season.toLowerCase();
   if(s.contains("black") && se.contains("kharif")){crop="Cotton";reason="Black soil and Kharif season are commonly suitable for cotton.";}
   else if(s.contains("alluvial") && w.contains("high")){crop="Rice";reason="High water availability and fertile alluvial soil support rice.";}
   else if(s.contains("red") && se.contains("rabi")){crop="Groundnut";reason="Red soil can support groundnut under suitable Rabi conditions.";}
   else if(s.contains("loamy")){crop="Wheat";reason="Loamy soil is generally suitable for wheat when the season and water supply match.";}
   else if(w.contains("low") || w.contains("rain")){crop="Millets";reason="Millets are a practical lower-water crop choice.";}
   return Map.of("recommendedCrop",crop,"reason",reason,"note","Rule-based starter recommendation. Confirm with local agricultural guidance before planting.");
 }

 @GetMapping("/fertilizer-recommendation")
 public Map<String,Object> fertilizerRecommendation(@RequestParam String cropName){
   String c=cropName.toLowerCase(); String fertilizer="Balanced NPK (10:10:10)"; String reason="Use a balanced fertilizer only after considering a soil test.";
   if(c.contains("rice")) {fertilizer="Urea + DAP / suitable NPK"; reason="Rice commonly needs nitrogen and phosphorus management across growth stages.";}
   else if(c.contains("cotton")) {fertilizer="NPK with nitrogen and potassium"; reason="Cotton benefits from balanced nutrition with attention to nitrogen and potassium.";}
   else if(c.contains("wheat")) {fertilizer="DAP + Urea"; reason="Wheat commonly uses phosphorus at establishment and nitrogen during growth.";}
   else if(c.contains("groundnut")) {fertilizer="Gypsum + balanced NPK"; reason="Groundnut nutrition often includes calcium/sulphur support in addition to balanced nutrients.";}
   else if(c.contains("maize")) {fertilizer="NPK + split nitrogen"; reason="Maize often benefits from balanced starter nutrition and split nitrogen applications.";}
   else if(c.contains("millet")) {fertilizer="Balanced NPK / organic manure"; reason="Millets generally need moderate, balanced nutrition based on soil fertility.";}
   return Map.of("fertilizer",fertilizer,"reason",reason,"note","Starter recommendation only. Use a soil test and local agronomist guidance for exact dose.");
 }

 @GetMapping("/irrigation-plan")
 public Map<String,Object> irrigationPlan(@RequestParam String cropName){
   String c=cropName.toLowerCase(); String frequency="Every 5–7 days", method="Drip or sprinkler", quantity="1500–2500";
   if(c.contains("rice")){frequency="Maintain consistent field moisture as required by the rice stage";method="Controlled flooding / suitable irrigation";quantity="2500–5000";}
   else if(c.contains("cotton")){frequency="Every 5–8 days depending on soil and weather";method="Drip irrigation";quantity="1500–2500";}
   else if(c.contains("wheat")){frequency="Every 10–15 days depending on growth stage";method="Furrow or sprinkler";quantity="1200–2200";}
   else if(c.contains("groundnut")){frequency="Every 7–10 days depending on soil moisture";method="Drip or sprinkler";quantity="1200–2000";}
   else if(c.contains("millet")){frequency="Every 7–12 days when rainfall is insufficient";method="Drip or sprinkler";quantity="800–1500";}
   return Map.of("frequency",frequency,"method",method,"waterQuantity",quantity,"note","Starter scheduling guidance. Adjust for rainfall, soil moisture, crop stage and local recommendations.");
 }

 @GetMapping("/profit/{farmId}")
 public Map<String,Object> profit(@PathVariable Long farmId){
   List<Crop> cs=crops.findByFarmId(farmId); List<Expense> es=expenses.findByFarmId(farmId);
   double revenue=cs.stream().mapToDouble(c->c.getExpectedRevenue()==null?0:c.getExpectedRevenue()).sum();
   double expense=es.stream().mapToDouble(e->e.getAmount()==null?0:e.getAmount()).sum();
   return Map.of("expectedRevenue",revenue,"totalExpenses",expense,"estimatedProfit",revenue-expense);
 }

 @GetMapping("/dashboard/{userId}")
 public Map<String,Object> dashboard(@PathVariable Long userId){
   List<Farm> fs=farms.findByUserId(userId); List<Crop> cs=new ArrayList<>(); List<Expense> es=new ArrayList<>(); long irrigationCount=0;
   for(Farm f:fs){cs.addAll(crops.findByFarmId(f.getId()));es.addAll(expenses.findByFarmId(f.getId()));for(Crop c:csFor(f.getId())) irrigationCount += irrigation.findByCropId(c.getId()).size();}
   long active=cs.stream().filter(c->"active".equalsIgnoreCase(c.getStatus())).count();
   double expense=es.stream().mapToDouble(e->e.getAmount()==null?0:e.getAmount()).sum();
   double revenue=cs.stream().mapToDouble(c->c.getExpectedRevenue()==null?0:c.getExpectedRevenue()).sum();
   return Map.of("farms",fs.size(),"crops",cs.size(),"activeCrops",active,"irrigationActivities",irrigationCount,"totalExpenses",expense,"expectedRevenue",revenue,"expectedProfit",revenue-expense);
 }
 private List<Crop> csFor(Long farmId){return crops.findByFarmId(farmId);}
}
