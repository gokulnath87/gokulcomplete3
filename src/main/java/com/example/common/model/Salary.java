package com.example.common.model;


public class Salary {
    private int salaryId;
    private int amount;

    public Salary(int salaryId, int amount) {
        this.salaryId = salaryId;
        this.amount = amount;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", amount=" + amount +
                '}';
    }

    public Salary() {
    }
}
