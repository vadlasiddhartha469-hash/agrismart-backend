package com.agrismart.agrismart.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="irrigation")
public class Irrigation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private LocalDate irrigationDate;
    private Double waterQuantity;
    private String method;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="crop_id", nullable=false) private Crop crop;
    public Irrigation(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public LocalDate getIrrigationDate(){return irrigationDate;} public void setIrrigationDate(LocalDate v){irrigationDate=v;}
    public Double getWaterQuantity(){return waterQuantity;} public void setWaterQuantity(Double v){waterQuantity=v;}
    public String getMethod(){return method;} public void setMethod(String v){method=v;}
    public Crop getCrop(){return crop;} public void setCrop(Crop v){crop=v;}
}
