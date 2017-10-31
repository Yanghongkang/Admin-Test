package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_renal_func")
public class RenalFunc extends DataCommon {

    private BigDecimal scr;
    private BigDecimal bun;
    private BigDecimal bua;

	public RenalFunc() {
	}

	public RenalFunc(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(scr == null || bun == null || bua == null){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public BigDecimal getScr() {
        return scr;
    }

    public void setScr(BigDecimal scr) {
        this.scr = scr;
    }

    public BigDecimal getBun() {
        return bun;
    }

    public void setBun(BigDecimal bun) {
        this.bun = bun;
    }

    public BigDecimal getBua() {
        return bua;
    }

    public void setBua(BigDecimal bua) {
        this.bua = bua;
    }
}