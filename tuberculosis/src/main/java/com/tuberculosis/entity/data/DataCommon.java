package com.tuberculosis.entity.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 数据通用表
 */
@MappedSuperclass
public abstract class DataCommon {
	private Long ptInfoId;
	private Long mxId;
    private Date date;
    protected Long id;

	public DataCommon() {
	}

	public DataCommon(Long id) {
		this.id = id;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPtInfoId() {
        return ptInfoId;
    }

    public void setPtInfoId(Long ptInfoId) {
        this.ptInfoId = ptInfoId;
    }

    public Long getMxId() {
        return mxId;
    }

    public void setMxId(Long mxId) {
        this.mxId = mxId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getDate() {
        return date;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setDate(Date date) {
        this.date = date;
    }
}