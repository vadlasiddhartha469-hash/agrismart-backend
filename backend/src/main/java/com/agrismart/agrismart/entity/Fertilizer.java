package com.agrismart.agrismart.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="fertilizers")
public class Fertilizer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String fertilizerName;
    private Double quantity;
    private LocalDate applicationDate;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="crop_id", nullable=false) private Crop crop;
    public Fertilizer(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getFertilizerName(){return fertilizerName;} public void setFertilizerName(String v){fertilizerName=v;}
    public Double getQuantity(){return quantity;} public void setQuantity(Double v){quantity=v;}
    public LocalDate getApplicationDate(){return applicationDate;} public void setApplicationDate(LocalDate v){applicationDate=v;}
    public Crop getCrop(){return crop;} public void setCrop(Crop v){crop=v;}
}
