package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_tb_diagnosis")
public class TbDiagnosis extends DataCommon {

    private int tbDiagnosis;
    private String otherResult;

	public TbDiagnosis() {
	}

	public TbDiagnosis(Long id) {
		this.id = id;
	}

    public int getTbDiagnosis() {
        return tbDiagnosis;
    }

    public void setTbDiagnosis(int tbDiagnosis) {
        this.tbDiagnosis = tbDiagnosis;
    }

    public String getOtherResult() {
        return otherResult;
    }

    public void setOtherResult(String otherResult) {
        this.otherResult = otherResult;
    }
}