package com.tuberculosis.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_hospital")
public class Hospital extends IdEntity {
	private String name;

	public Hospital() {
	}

	public Hospital(Long id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}