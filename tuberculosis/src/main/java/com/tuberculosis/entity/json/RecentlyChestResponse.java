package com.tuberculosis.entity.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RecentlyChestResponse {

    private String cardResult;
    private String image;
    private String lastImage;
    private String resultStr;
    private int cured;
    private int byo;
    private Date byoDate;

	public RecentlyChestResponse() {
	}

    public String getCardResult() {
        return cardResult;
    }

    public void setCardResult(String cardResult) {
        this.cardResult = cardResult;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastImage() {
        return lastImage;
    }

    public void setLastImage(String lastImage) {
        this.lastImage = lastImage;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }

    public int getByo() {
        return byo;
    }

    public void setByo(int byo) {
        this.byo = byo;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getByoDate() {
        return byoDate;
    }

    public void setByoDate(Date byoDate) {
        this.byoDate = byoDate;
    }
}