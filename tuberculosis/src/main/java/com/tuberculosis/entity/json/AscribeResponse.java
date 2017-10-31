package com.tuberculosis.entity.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuberculosis.entity.data.DrugScpt;
import com.tuberculosis.entity.data.GeneChip;
import com.tuberculosis.entity.data.GeneXpert;
import com.tuberculosis.entity.data.Hain;

import java.util.Date;
import java.util.List;

/**
 * @author Li Shaoqing
 */
public class AscribeResponse extends ValidatorError{

    private Date beginDate;
    private Date endDate;

    public AscribeResponse() {}

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}