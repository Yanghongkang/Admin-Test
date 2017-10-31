package com.tuberculosis.entity.data;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_drug_scpt")
public class DrugScpt extends DataCommon {

    private int dsType;
    private String dsResult;

	public DrugScpt() {
	}

	public DrugScpt(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(dsType <= 0 || StringUtils.isBlank(dsResult)){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public int getDsType() {
        return dsType;
    }

    public void setDsType(int dsType) {
        this.dsType = dsType;
    }

    public String getDsResult() {
        return dsResult;
    }

    public void setDsResult(String dsResult) {
        this.dsResult = dsResult;
    }
}