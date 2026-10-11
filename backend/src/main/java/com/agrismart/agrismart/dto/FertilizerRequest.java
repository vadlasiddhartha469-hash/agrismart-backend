package com.agrismart.agrismart.dto;
import java.time.LocalDate;
public class FertilizerRequest {
    public String fertilizerName;
    public Double quantity;
    public LocalDate applicationDate;
    public Long cropId;
}
