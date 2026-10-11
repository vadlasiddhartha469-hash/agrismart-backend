package com.agrismart.agrismart.dto;
import java.time.LocalDate;
public class CropRequest {
    public String cropName, status;
    public LocalDate sowingDate, expectedHarvestDate;
    public Double expectedYield, expectedRevenue;
    public Long farmId;
}
