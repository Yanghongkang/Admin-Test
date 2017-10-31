package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_hiv_test")
public class HivTest extends DataCommon {

    private int hivResult;

	public HivTest() {
	}

	public HivTest(Long id) {
		this.id = id;
	}

    public int getHivResult() {
        return hivResult;
    }

    public void setHivResult(int hivResult) {
        this.hivResult = hivResult;
    }
}