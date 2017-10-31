package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_trans_inst")
public class TransInst extends DataCommon {

    private String origInst;
    private int tbhxType;
    private int refReason;
    private String recvInst;
    private String attdMd;
    private int transInstType;
    private String receiveDoctor;

	public TransInst() {
	}

	public TransInst(Long id) {
		this.id = id;
	}

    public String getOrigInst() {
        return origInst;
    }

    public void setOrigInst(String origInst) {
        this.origInst = origInst;
    }

    public int getTbhxType() {
        return tbhxType;
    }

    public void setTbhxType(int tbhxType) {
        this.tbhxType = tbhxType;
    }

    public int getRefReason() {
        return refReason;
    }

    public void setRefReason(int refReason) {
        this.refReason = refReason;
    }

    public String getRecvInst() {
        return recvInst;
    }

    public void setRecvInst(String recvInst) {
        this.recvInst = recvInst;
    }

    public String getAttdMd() {
        return attdMd;
    }

    public void setAttdMd(String attdMd) {
        this.attdMd = attdMd;
    }

    public int getTransInstType() {
        return transInstType;
    }

    public void setTransInstType(int transInstType) {
        this.transInstType = transInstType;
    }

    public String getReceiveDoctor() {
        return receiveDoctor;
    }

    public void setReceiveDoctor(String receiveDoctor) {
        this.receiveDoctor = receiveDoctor;
    }
}