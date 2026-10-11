package com.agrismart.agrismart.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="expenses")
public class Expense {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String category;
    private Double amount;
    private LocalDate expenseDate;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="farm_id", nullable=false) private Farm farm;
    public Expense(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public Double getAmount(){return amount;} public void setAmount(Double v){amount=v;}
    public LocalDate getExpenseDate(){return expenseDate;} public void setExpenseDate(LocalDate v){expenseDate=v;}
    public Farm getFarm(){return farm;} public void setFarm(Farm v){farm=v;}
}
