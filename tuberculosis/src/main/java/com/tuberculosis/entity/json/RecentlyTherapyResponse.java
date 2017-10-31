package com.tuberculosis.entity.json;

public class RecentlyTherapyResponse {

    private String recentlyCurrTherapy;
    private String recentlyAdjReason;
    private String recentlyTherapy;
    private int recentlyTherapyStatus;
    private String recentlyStopReason;

	public RecentlyTherapyResponse() {
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
}