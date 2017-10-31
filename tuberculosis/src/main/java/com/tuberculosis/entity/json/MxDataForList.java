package com.tuberculosis.entity.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MxDataForList {
    private Long id;
    private Date date;
    private String mx;
    private int tbDiagnosis;
    private Long ptInfoId;
    private int importanceMonth;
    private int dataIncomplete;
    private int dataStatus;   // o 灰色，日期未到；1 蓝色已随访； 2 红色已随访
    private String checkItem;   // o 灰色，日期未到；1 蓝色已随访； 2 红色已随访
    
    private int isdosage;
    public int getIsdosage() {
		return isdosage;
	}

	public void setIsdosage(int isdosage) {
		this.isdosage = isdosage;
	}
	public MxDataForList() {
	}

	public MxDataForList(Long id) {
		this.id = id;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMx() {
        return mx;
    }

    public void setMx(String mx) {
        this.mx = mx;
    }

    public int getImportanceMonth() {
        return importanceMonth;
    }

    public void setImportanceMonth(int importanceMonth) {
        this.importanceMonth = importanceMonth;
    }

    public int getDataIncomplete() {
        return dataIncomplete;
    }

    public void setDataIncomplete(int dataIncomplete) {
        this.dataIncomplete = dataIncomplete;
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

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }
}