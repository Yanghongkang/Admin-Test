package com.tuberculosis.entity.data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_physical_exam")
public class PhysicalExam extends DataCommon {

    private BigDecimal wgt;
    private BigDecimal hgt;//2017.6.14添加身高字段

	public PhysicalExam() {
	}

	public PhysicalExam(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(wgt == null){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public BigDecimal getWgt() {
        return wgt;
    }

    public void setWgt(BigDecimal wgt) {
        this.wgt = wgt;
    }

	public BigDecimal getHgt() {
		return hgt;
	}

	public void setHgt(BigDecimal hgt) {
		this.hgt = hgt;
	}
    
    
    
}