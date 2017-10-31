package com.tuberculosis.entity.json;

import com.tuberculosis.entity.data.Assessment;
import com.tuberculosis.entity.data.SputumCulture;
import com.tuberculosis.entity.data.SputumSmear;

public class MxDataReport extends  MxDataForList{
    private int therapy;
    private String currTherapy;
    private SputumSmear sputumSmear;
    private SputumCulture sputumCulture;
    private Assessment assessment;

	public MxDataReport() {
	}

    public int getTherapy() {
        return therapy;
    }

    public void setTherapy(int therapy) {
        this.therapy = therapy;
    }

    public String getCurrTherapy() {
        return currTherapy;
    }

    public void setCurrTherapy(String currTherapy) {
        this.currTherapy = currTherapy;
    }

    public SputumSmear getSputumSmear() {
        return sputumSmear;
    }

    public void setSputumSmear(SputumSmear sputumSmear) {
        this.sputumSmear = sputumSmear;
    }
    public SputumCulture getSputumCulture() {
        return sputumCulture;
    }

    public void setSputumCulture(SputumCulture sputumCulture) {
        this.sputumCulture = sputumCulture;
    }

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}
    
    
}