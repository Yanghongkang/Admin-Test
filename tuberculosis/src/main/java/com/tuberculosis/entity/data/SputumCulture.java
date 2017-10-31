package com.tuberculosis.entity.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_sputum_culture")
public class SputumCulture extends DataCommon {

    private int spcuResult;
    private String spcuResultStr;
    private int cultureNum;

	public SputumCulture() {
	}

	public SputumCulture(Long id) {
		this.id = id;
	}

    public int getSpcuResult() {
        return spcuResult;
    }

    public void setSpcuResult(int spcuResult) {
        this.spcuResult = spcuResult;
    }

    public String getSpcuResultStr() {
        return spcuResultStr;
    }

    public void setSpcuResultStr(String spcuResultStr) {
        this.spcuResultStr = spcuResultStr;
    }

    public int getCultureNum() {
        return cultureNum;
    }

    public void setCultureNum(int cultureNum) {
        this.cultureNum = cultureNum;
    }
}