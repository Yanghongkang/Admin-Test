package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_genexpert")
public class GeneXpert extends DataCommon {

    private String gxResult;

	public GeneXpert() {
	}

	public GeneXpert(Long id) {
		this.id = id;
	}

    public String getGxResult() {
        return gxResult;
    }

    public void setGxResult(String gxResult) {
        this.gxResult = gxResult;
    }
}