package com.agrismart.agrismart.dto;
import java.time.LocalDate;
public class IrrigationRequest {
    public LocalDate irrigationDate;
    public Double waterQuantity;
    public String method;
    public Long cropId;
}
