package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_blood_routine")
public class BloodRoutine extends DataCommon {

    private BigDecimal wbc;
    private BigDecimal rbc;
    private BigDecimal hb;
    private BigDecimal plt;

	public BloodRoutine() {
	}

	public BloodRoutine(Long id) {
		this.id = id;
	}

    public BigDecimal getWbc() {
        return wbc;
    }

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(wbc == null || rbc == null || hb == null || plt == null){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public void setWbc(BigDecimal wbc) {
        this.wbc = wbc;
    }

    public BigDecimal getRbc() {
        return rbc;
    }

    public void setRbc(BigDecimal rbc) {
        this.rbc = rbc;
    }

    public BigDecimal getPlt() {
        return plt;
    }

    public void setPlt(BigDecimal plt) {
        this.plt = plt;
    }

    public BigDecimal getHb() {
        return hb;
    }

    public void setHb(BigDecimal hb) {
        this.hb = hb;
    }
}