package com.tuberculosis.entity.json;

/**
 * @author Li Shaoqing
 * 用户
 */
public class AdminSettingResponse extends ValidatorError{

    private String adminName;
    private String mobile;
    private String address;
    private String telephone;

	public AdminSettingResponse() {}

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}