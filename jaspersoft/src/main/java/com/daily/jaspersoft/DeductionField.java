package com.daily.jaspersoft;

import java.math.BigDecimal;

public class DeductionField {
    private String fdSubName;
    private BigDecimal fdSubDays;
    private BigDecimal fdSubAmount;

    public String getFdSubName() {
        return fdSubName;
    }

    public void setFdSubName(String fdSubName) {
        this.fdSubName = fdSubName;
    }

    public BigDecimal getFdSubDays() {
        return fdSubDays;
    }

    public void setFdSubDays(BigDecimal fdSubDays) {
        this.fdSubDays = fdSubDays;
    }

    public BigDecimal getFdSubAmount() {
        return fdSubAmount;
    }

    public void setFdSubAmount(BigDecimal fdSubAmount) {
        this.fdSubAmount = fdSubAmount;
    }

    public DeductionField(String fdSubName, BigDecimal fdSubDays, BigDecimal fdSubAmount) {
        this.fdSubName = fdSubName;
        this.fdSubDays = fdSubDays;
        this.fdSubAmount = fdSubAmount;
    }
}
