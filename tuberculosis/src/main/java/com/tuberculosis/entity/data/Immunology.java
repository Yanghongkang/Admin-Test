package com.tuberculosis.entity.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_immunology")
public class Immunology extends DataCommon {

    private BigDecimal hbsAg;
    private BigDecimal hbsAb;
    private BigDecimal hbeAg;
    private BigDecimal hbeAb;
    private BigDecimal hbcAb;
    private BigDecimal hbcAbIgm;
    private BigDecimal hcvAb;
    private BigDecimal antiHiv;
    private BigDecimal tpAb;
    private BigDecimal tpRpr;

    private int hbsAgBoolean;
    private int hbsAbBoolean;
    private int hbeAgBoolean;
    private int hbeAbBoolean;
    private int hbcAbBoolean;
    private int hbcAbIgmBoolean;
    private int hcvAbBoolean;
    private int antiHivBoolean;
    private int tpAbBoolean;
    private int tpRprBoolean;


    public Immunology() {
	}

	public Immunology(Long id) {
		this.id = id;
	}

    @JsonIgnore
    public BigDecimal getHbsAg() {
        return hbsAg;
    }

    public void setHbsAg(BigDecimal hbsAg) {
        this.hbsAg = hbsAg;
    }
    @JsonIgnore
    public BigDecimal getHbsAb() {
        return hbsAb;
    }

    public void setHbsAb(BigDecimal hbsAb) {
        this.hbsAb = hbsAb;
    }
    @JsonIgnore
    public BigDecimal getHbeAg() {
        return hbeAg;
    }

    public void setHbeAg(BigDecimal hbeAg) {
        this.hbeAg = hbeAg;
    }
    @JsonIgnore
    public BigDecimal getHbeAb() {
        return hbeAb;
    }

    public void setHbeAb(BigDecimal hbeAb) {
        this.hbeAb = hbeAb;
    }
    @JsonIgnore
    public BigDecimal getHbcAb() {
        return hbcAb;
    }

    public void setHbcAb(BigDecimal hbcAb) {
        this.hbcAb = hbcAb;
    }
    @JsonIgnore
    public BigDecimal getHbcAbIgm() {
        return hbcAbIgm;
    }

    public void setHbcAbIgm(BigDecimal hbcAbIgm) {
        this.hbcAbIgm = hbcAbIgm;
    }
    @JsonIgnore
    public BigDecimal getHcvAb() {
        return hcvAb;
    }

    public void setHcvAb(BigDecimal hcvAb) {
        this.hcvAb = hcvAb;
    }
    @JsonIgnore
    public BigDecimal getAntiHiv() {
        return antiHiv;
    }

    public void setAntiHiv(BigDecimal antiHiv) {
        this.antiHiv = antiHiv;
    }
    @JsonIgnore
    public BigDecimal getTpAb() {
        return tpAb;
    }

    public void setTpAb(BigDecimal tpAb) {
        this.tpAb = tpAb;
    }
    @JsonIgnore
    public BigDecimal getTpRpr() {
        return tpRpr;
    }

    public void setTpRpr(BigDecimal tpRpr) {
        this.tpRpr = tpRpr;
    }

    public int getHbsAgBoolean() {
        return hbsAgBoolean;
    }

    public void setHbsAgBoolean(int hbsAgBoolean) {
        this.hbsAgBoolean = hbsAgBoolean;
    }

    public int getHbsAbBoolean() {
        return hbsAbBoolean;
    }

    public void setHbsAbBoolean(int hbsAbBoolean) {
        this.hbsAbBoolean = hbsAbBoolean;
    }

    public int getHbeAgBoolean() {
        return hbeAgBoolean;
    }

    public void setHbeAgBoolean(int hbeAgBoolean) {
        this.hbeAgBoolean = hbeAgBoolean;
    }

    public int getHbeAbBoolean() {
        return hbeAbBoolean;
    }

    public void setHbeAbBoolean(int hbeAbBoolean) {
        this.hbeAbBoolean = hbeAbBoolean;
    }

    public int getHbcAbBoolean() {
        return hbcAbBoolean;
    }

    public void setHbcAbBoolean(int hbcAbBoolean) {
        this.hbcAbBoolean = hbcAbBoolean;
    }

    public int getHbcAbIgmBoolean() {
        return hbcAbIgmBoolean;
    }

    public void setHbcAbIgmBoolean(int hbcAbIgmBoolean) {
        this.hbcAbIgmBoolean = hbcAbIgmBoolean;
    }

    public int getHcvAbBoolean() {
        return hcvAbBoolean;
    }

    public void setHcvAbBoolean(int hcvAbBoolean) {
        this.hcvAbBoolean = hcvAbBoolean;
    }

    public int getAntiHivBoolean() {
        return antiHivBoolean;
    }

    public void setAntiHivBoolean(int antiHivBoolean) {
        this.antiHivBoolean = antiHivBoolean;
    }

    public int getTpAbBoolean() {
        return tpAbBoolean;
    }

    public void setTpAbBoolean(int tpAbBoolean) {
        this.tpAbBoolean = tpAbBoolean;
    }

    public int getTpRprBoolean() {
        return tpRprBoolean;
    }

    public void setTpRprBoolean(int tpRprBoolean) {
        this.tpRprBoolean = tpRprBoolean;
    }
}