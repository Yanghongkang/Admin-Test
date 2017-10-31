package com.tuberculosis.entity.json;

/**
 * @author Li Shaoqing
 */
public class CommonResponse extends ValidatorError{

	private String status;

	public CommonResponse() {}

    public CommonResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}