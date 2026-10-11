package com.agrismart.agrismart.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="crops")
public class Crop {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String cropName;
    private LocalDate sowingDate;
    private LocalDate expectedHarvestDate;
    private String status;
    private Double expectedYield;
    private Double expectedRevenue;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="farm_id", nullable=false) private Farm farm;
    public Crop(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getCropName(){return cropName;} public void setCropName(String v){cropName=v;}
    public LocalDate getSowingDate(){return sowingDate;} public void setSowingDate(LocalDate v){sowingDate=v;}
    public LocalDate getExpectedHarvestDate(){return expectedHarvestDate;} public void setExpectedHarvestDate(LocalDate v){expectedHarvestDate=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public Double getExpectedYield(){return expectedYield;} public void setExpectedYield(Double v){expectedYield=v;}
    public Double getExpectedRevenue(){return expectedRevenue;} public void setExpectedRevenue(Double v){expectedRevenue=v;}
    public Farm getFarm(){return farm;} public void setFarm(Farm v){farm=v;}
}
