package com.tuberculosis.entity.data;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_gene_chip")
public class GeneChip extends DataCommon {

    private int gchipResult;
    private String gchipOthbac;

	public GeneChip() {
	}

	public GeneChip(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(gchipResult <= 0 || StringUtils.isBlank(gchipOthbac)){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
    }

    public int getGchipResult() {
        return gchipResult;
    }

    public void setGchipResult(int gchipResult) {
        this.gchipResult = gchipResult;
    }

    public String getGchipOthbac() {
        return gchipOthbac;
    }

    public void setGchipOthbac(String gchipOthbac) {
        this.gchipOthbac = gchipOthbac;
    }
}