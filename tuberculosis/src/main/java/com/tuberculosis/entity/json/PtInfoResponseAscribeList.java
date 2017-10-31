package com.tuberculosis.entity.json;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class PtInfoResponseAscribeList extends ValidatorError{

    private List<PtInfoResponse> ptInfoResponse;

	public PtInfoResponseAscribeList() {}

    public PtInfoResponseAscribeList(List<PtInfoResponse> ptInfoResponse) {
        this.ptInfoResponse = ptInfoResponse;
    }

    public List<PtInfoResponse> getPtInfoResponse() {
        return ptInfoResponse;
    }

    public void setPtInfoResponse(List<PtInfoResponse> ptInfoResponse) {
        this.ptInfoResponse = ptInfoResponse;
    }
}