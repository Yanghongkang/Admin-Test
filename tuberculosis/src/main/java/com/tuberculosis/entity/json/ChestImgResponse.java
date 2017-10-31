package com.tuberculosis.entity.json;

/**
 * @author Li Shaoqing
 */
public class ChestImgResponse extends ValidatorError{

	private String oneImgUrl;
	private String twoImgUrl;

	public ChestImgResponse() {}

    public String getTwoImgUrl() {
        return twoImgUrl;
    }

    public void setTwoImgUrl(String twoImgUrl) {
        this.twoImgUrl = twoImgUrl;
    }

    public String getOneImgUrl() {
        return oneImgUrl;
    }

    public void setOneImgUrl(String oneImgUrl) {
        this.oneImgUrl = oneImgUrl;
    }
}