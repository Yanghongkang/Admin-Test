package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_hain")
public class Hain extends DataCommon {

    private String hainResult;

	public Hain() {
	}

	public Hain(Long id) {
		this.id = id;
	}

    public String getHainResult() {
        return hainResult;
    }

    public void setHainResult(String hainResult) {
        this.hainResult = hainResult;
    }
}