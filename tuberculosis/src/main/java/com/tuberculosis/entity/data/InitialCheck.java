package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_inital_check")
public class InitialCheck extends DataCommon {

    private String potassium;
    private String tsh;
    private String hearing;
    private String focCv;

	public InitialCheck() {
	}

	public InitialCheck(Long id) {
		this.id = id;
	}

    public String getPotassium() {
        return potassium;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    public String getTsh() {
        return tsh;
    }

    public void setTsh(String tsh) {
        this.tsh = tsh;
    }

    public String getHearing() {
        return hearing;
    }

    public void setHearing(String hearing) {
        this.hearing = hearing;
    }

    public String getFocCv() {
        return focCv;
    }

    public void setFocCv(String focCv) {
        this.focCv = focCv;
    }

}