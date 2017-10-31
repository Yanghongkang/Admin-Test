package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_liver_func")
public class LiverFunc extends DataCommon {

    private BigDecimal alt;
    private BigDecimal ast;
    private BigDecimal tbil;
    private BigDecimal yGt;
    private BigDecimal alp;
    private BigDecimal alb;
    private BigDecimal glu;
    private BigDecimal dbil;

	public LiverFunc() {
	}

	public LiverFunc(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(alt == null || ast == null || tbil == null || yGt == null || alp == null || alb == null || glu == null || dbil == null){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public BigDecimal getAlt() {
        return alt;
    }

    public void setAlt(BigDecimal alt) {
        this.alt = alt;
    }

    public BigDecimal getAst() {
        return ast;
    }

    public void setAst(BigDecimal ast) {
        this.ast = ast;
    }

    public BigDecimal getTbil() {
        return tbil;
    }

    public void setTbil(BigDecimal tbil) {
        this.tbil = tbil;
    }

    public BigDecimal getyGt() {
        return yGt;
    }

    public void setyGt(BigDecimal yGt) {
        this.yGt = yGt;
    }

    public BigDecimal getAlp() {
        return alp;
    }

    public void setAlp(BigDecimal alp) {
        this.alp = alp;
    }

    public BigDecimal getAlb() {
        return alb;
    }

    public void setAlb(BigDecimal alb) {
        this.alb = alb;
    }

    public BigDecimal getGlu() {
        return glu;
    }

    public void setGlu(BigDecimal glu) {
        this.glu = glu;
    }

    public BigDecimal getDbil() {
        return dbil;
    }

    public void setDbil(BigDecimal dbil) {
        this.dbil = dbil;
    }
}