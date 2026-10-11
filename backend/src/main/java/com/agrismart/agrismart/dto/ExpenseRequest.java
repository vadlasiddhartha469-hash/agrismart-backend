package com.agrismart.agrismart.dto;
import java.time.LocalDate;
public class ExpenseRequest {
    public String category;
    public Double amount;
    public LocalDate expenseDate;
    public Long farmId;
}
