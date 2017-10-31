package com.tuberculosis.entity.json;

public class CreateSearchResponse {
    private int hasData;
    private int hasInit;
    private String currTherapy;
    private String adjReason;
    private String therapy;
    private int therapyStatus;
    private int hasTherapy;
    private String stopReason;
    private String recentlyCurrTherapy;
    private String recentlyAdjReason;
    private String recentlyTherapy;
    private int recentlyTherapyStatus;
    private String recentlyStopReason;
    private String chestResult;
    private String firstChestResult;
    private int tbDiagnosis;

	public CreateSearchResponse() {
	}

    public int getHasTherapy() {
        return hasTherapy;
    }

    public void setHasTherapy(int hasTherapy) {
        this.hasTherapy = hasTherapy;
    }

    public String getRecentlyCurrTherapy() {
        return recentlyCurrTherapy;
    }

    public void setRecentlyCurrTherapy(String recentlyCurrTherapy) {
        this.recentlyCurrTherapy = recentlyCurrTherapy;
    }

    public String getRecentlyAdjReason() {
        return recentlyAdjReason;
    }

    public void setRecentlyAdjReason(String recentlyAdjReason) {
        this.recentlyAdjReason = recentlyAdjReason;
    }

    public String getRecentlyTherapy() {
        return recentlyTherapy;
    }

    public void setRecentlyTherapy(String recentlyTherapy) {
        this.recentlyTherapy = recentlyTherapy;
    }

    public int getRecentlyTherapyStatus() {
        return recentlyTherapyStatus;
    }

    public void setRecentlyTherapyStatus(int recentlyTherapyStatus) {
        this.recentlyTherapyStatus = recentlyTherapyStatus;
    }

    public String getRecentlyStopReason() {
        return recentlyStopReason;
    }

    public void setRecentlyStopReason(String recentlyStopReason) {
        this.recentlyStopReason = recentlyStopReason;
    }

    public int getHasData() {
        return hasData;
    }

    public void setHasData(int hasData) {
        this.hasData = hasData;
    }

    public int getHasInit() {
        return hasInit;
    }

    public void setHasInit(int hasInit) {
        this.hasInit = hasInit;
    }

    public String getAdjReason() {
        return adjReason;
    }

    public void setAdjReason(String adjReason) {
        this.adjReason = adjReason;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public int getTherapyStatus() {
        return therapyStatus;
    }

    public void setTherapyStatus(int therapyStatus) {
        this.therapyStatus = therapyStatus;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public String getCurrTherapy() {
        return currTherapy;
    }

    public void setCurrTherapy(String currTherapy) {
        this.currTherapy = currTherapy;
    }

    public String getChestResult() {
        return chestResult;
    }

    public void setChestResult(String chestResult) {
        this.chestResult = chestResult;
    }

    public String getFirstChestResult() {
        return firstChestResult;
    }

    public void setFirstChestResult(String firstChestResult) {
        this.firstChestResult = firstChestResult;
    }

    public int getTbDiagnosis() {
        return tbDiagnosis;
    }

    public void setTbDiagnosis(int tbDiagnosis) {
        this.tbDiagnosis = tbDiagnosis;
    }
}