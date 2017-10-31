package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_diabetes_test")
public class DiabetesTest extends DataCommon {

    private int dmResult;

	public DiabetesTest() {
	}

	public DiabetesTest(Long id) {
		this.id = id;
	}

    public int getDmResult() {
        return dmResult;
    }

    public void setDmResult(int dmResult) {
        this.dmResult = dmResult;
    }
}