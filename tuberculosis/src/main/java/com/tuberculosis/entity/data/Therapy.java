package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_therapy")
public class Therapy extends DataCommon {

    private String currTherapy;
    private String adjReason;
    private String therapy;
    private int therapyStatus;
    private String stopReason;

	public Therapy() {
	}

	public Therapy(Long id) {
		this.id = id;
	}

    public String getCurrTherapy() {
        return currTherapy;
    }

    public void setCurrTherapy(String currTherapy) {
        this.currTherapy = currTherapy;
    }

    public String getAdjReason() {
        return adjReason;
    }

    public void setAdjReason(String adjReason) {
        this.adjReason = adjReason;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public int getTherapyStatus() {
        return therapyStatus;
    }

    public void setTherapyStatus(int therapyStatus) {
        this.therapyStatus = therapyStatus;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }
}