package com.tuberculosis.entity.json;

import com.tuberculosis.entity.MxData;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class MxDataResponse extends ValidatorError{

    private List<MxData> mxDataList;

	public MxDataResponse() {}

    public MxDataResponse(List<MxData> mxDataList) {
        this.mxDataList = mxDataList;
    }

    public List<MxData> getMxDataList() {
        return mxDataList;
    }

    public void setMxDataList(List<MxData> mxDataList) {
        this.mxDataList = mxDataList;
    }
}