package com.tuberculosis.entity.json;

import com.tuberculosis.entity.data.SputumCulture;
import com.tuberculosis.entity.data.SputumSmear;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class MxDataReportResponse extends ValidatorError{

    private List<MxDataReport> realMxDataList;
    private List<MxDataReport> baseMxDataList;

	public MxDataReportResponse() {}


    public List<MxDataReport> getBaseMxDataList() {
        return baseMxDataList;
    }

    public void setBaseMxDataList(List<MxDataReport> baseMxDataList) {
        this.baseMxDataList = baseMxDataList;
    }

    public List<MxDataReport> getRealMxDataList() {
        return realMxDataList;
    }

    public void setRealMxDataList(List<MxDataReport> realMxDataList) {
        this.realMxDataList = realMxDataList;
    }

}