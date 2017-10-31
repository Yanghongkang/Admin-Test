package com.tuberculosis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "t_mx_data")
public class MxData extends IdEntity {

    private String mx;
    private Date date;
    private int status;
    private int tbDiagnosis;
    private int therapy;
    private String currTherapy;
    private Long ptInfoId;
    private int isdosage;//0 提示     1 不提示
    
    

	public int getIsdosage() {
		return isdosage;
	}

	public void setIsdosage(int isdosage) {
		this.isdosage = isdosage;
	}

	public MxData() {
	}

	public MxData(Long id) {
		this.id = id;
	}

    @Transient
    public String getMx() {
        return mx;
    }

    public void setMx(String mx) {
        this.mx = mx;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getDate() {
        Timestamp stamp = new Timestamp(date.getTime());
        Date nDate = new Date(stamp.getTime());
        return nDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTbDiagnosis() {
        return tbDiagnosis;
    }

    public void setTbDiagnosis(int tbDiagnosis) {
        this.tbDiagnosis = tbDiagnosis;
    }

    public Long getPtInfoId() {
        return ptInfoId;
    }

    public void setPtInfoId(Long ptInfoId) {
        this.ptInfoId = ptInfoId;
    }

    public int getTherapy() {
        return therapy;
    }

    public void setTherapy(int therapy) {
        this.therapy = therapy;
    }

    public String getCurrTherapy() {
        return currTherapy;
    }

    public void setCurrTherapy(String currTherapy) {
        this.currTherapy = currTherapy;
    }
}