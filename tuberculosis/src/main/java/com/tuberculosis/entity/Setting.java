package com.tuberculosis.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_setting")
public class Setting extends IdEntity {
	private String adminName;
	private String mobile;
	private String address;
	private String telephone;

	public Setting() {
	}

	public Setting(Long id) {
		this.id = id;
	}

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