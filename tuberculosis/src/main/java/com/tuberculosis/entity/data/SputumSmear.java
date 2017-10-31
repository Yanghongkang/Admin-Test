package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_sputum_smear")
public class SputumSmear extends DataCommon {

    private int spsmResult;
    private int smearNum;

	public SputumSmear() {
	}

	public SputumSmear(Long id) {
		this.id = id;
	}

    public int getSpsmResult() {
        return spsmResult;
    }

    public void setSpsmResult(int spsmResult) {
        this.spsmResult = spsmResult;
    }

    public int getSmearNum() {
        return smearNum;
    }

    public void setSmearNum(int smearNum) {
        this.smearNum = smearNum;
    }
}