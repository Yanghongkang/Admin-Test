package com.tuberculosis.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_dosage")
public class Dosage extends IdEntity {
	private String name;
	private String value;
	private Date craetedate;

	public String getName() {	
		return name;
	}

	public String getValue() {
		return value;
	}

	

	public void setName(String name) {
		this.name = name;
	}
	
	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+08:00")
	public Date getCraetedate() {
		return craetedate;
	}

	public void setCraetedate(Date craetedate) {
		this.craetedate = craetedate;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
