package com.agrismart.agrismart.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="farms")
public class Farm {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String farmName;
    private String location;
    private Double area;
    private String soilType;
    private String waterAvailability;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false) private User user;
    public Farm() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getFarmName(){return farmName;} public void setFarmName(String v){farmName=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public Double getArea(){return area;} public void setArea(Double v){area=v;}
    public String getSoilType(){return soilType;} public void setSoilType(String v){soilType=v;}
    public String getWaterAvailability(){return waterAvailability;} public void setWaterAvailability(String v){waterAvailability=v;}
    public User getUser(){return user;} public void setUser(User v){user=v;}
}
