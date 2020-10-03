package com.daily.jaspersoft;

import java.math.BigDecimal;

public class AdditionField {
    private String fdAddName;
    private BigDecimal fdAddDays;
    private BigDecimal fdAddAmount;

    public String getFdAddName() {
        return fdAddName;
    }

    public void setFdAddName(String fdAddName) {
        this.fdAddName = fdAddName;
    }

    public BigDecimal getFdAddDays() {
        return fdAddDays;
    }

    public void setFdAddDays(BigDecimal fdAddDays) {
        this.fdAddDays = fdAddDays;
    }

    public BigDecimal getFdAddAmount() {
        return fdAddAmount;
    }

    public void setFdAddAmount(BigDecimal fdAddAmount) {
        this.fdAddAmount = fdAddAmount;
    }



    public AdditionField(String fdAddName, BigDecimal fdAddDays, BigDecimal fdAddAmount) {
        this.fdAddName = fdAddName;
        this.fdAddDays = fdAddDays;
        this.fdAddAmount = fdAddAmount;
    }
}
