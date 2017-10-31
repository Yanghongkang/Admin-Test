package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_urine_routine")
public class UrineRoutine extends DataCommon {

    private BigDecimal wbcu;
    private BigDecimal rbcu;
    private BigDecimal prou;
    private BigDecimal gluu;

	public UrineRoutine() {
	}

	public UrineRoutine(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(wbcu == null || rbcu == null || prou == null || gluu == null){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public BigDecimal getWbcu() {
        return wbcu;
    }

    public void setWbcu(BigDecimal wbcu) {
        this.wbcu = wbcu;
    }

    public BigDecimal getRbcu() {
        return rbcu;
    }

    public void setRbcu(BigDecimal rbcu) {
        this.rbcu = rbcu;
    }

    public BigDecimal getProu() {
        return prou;
    }

    public void setProu(BigDecimal prou) {
        this.prou = prou;
    }

    public BigDecimal getGluu() {
        return gluu;
    }

    public void setGluu(BigDecimal gluu) {
        this.gluu = gluu;
    }
}