package com.tuberculosis.entity.json;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class PtInfoResponseList extends ValidatorError{

    private List<PtInfoResponse> ptInfoResponse;
    private int totalPage;

	public PtInfoResponseList() {}

    public PtInfoResponseList(List<PtInfoResponse> ptInfoResponse) {
        this.ptInfoResponse = ptInfoResponse;
    }

    public List<PtInfoResponse> getPtInfoResponse() {
        return ptInfoResponse;
    }

    public void setPtInfoResponse(List<PtInfoResponse> ptInfoResponse) {
        this.ptInfoResponse = ptInfoResponse;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}