package com.tuberculosis.entity.json;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class MxDataForListResponse extends ValidatorError{

    private List<MxDataForList> mxDataList;
    private int ascribeStatus;

	public MxDataForListResponse() {}

    public MxDataForListResponse(List<MxDataForList> mxDataList) {
        this.mxDataList = mxDataList;
    }

    public List<MxDataForList> getMxDataList() {
        return mxDataList;
    }

    public void setMxDataList(List<MxDataForList> mxDataList) {
        this.mxDataList = mxDataList;
    }

    public int getAscribeStatus() {
        return ascribeStatus;
    }

    public void setAscribeStatus(int ascribeStatus) {
        this.ascribeStatus = ascribeStatus;
    }
}