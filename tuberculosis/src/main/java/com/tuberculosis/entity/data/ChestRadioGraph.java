package com.tuberculosis.entity.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_chest_radiograph")
public class ChestRadioGraph extends DataCommon {

    private String cardResult;
    private String image;
    private String lastImage;
    private String resultStr;
    private int cured;
    private int byo;
    private Date byoDate;

	public ChestRadioGraph() {
	}

	public ChestRadioGraph(Long id) {
		this.id = id;
	}

    public boolean checkDataIncomplete(){
        boolean isDataIncomplete = false;
        if(StringUtils.isBlank(cardResult) || StringUtils.isBlank(image)){
            isDataIncomplete = true;
        }
        return isDataIncomplete;
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

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setByoDate(Date byoDate) {
        this.byoDate = byoDate;
    }
}