package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_bacterial_type")
public class BacterialType extends DataCommon {

    private String result;

	public BacterialType() {
	}

	public BacterialType(Long id) {
		this.id = id;
	}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}