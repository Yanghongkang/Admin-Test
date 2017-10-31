package com.tuberculosis.entity.json;

import java.util.Date;
import java.util.List;

import com.tuberculosis.entity.Dosage;

/**
 * @author Li Shaoqing
 * 用户
 */
public class UserResponse extends ValidatorError{

	private String name;
    private String token;
    private Date expireDate;
    private String permission;
    private int firstLogin;
    private String recvInst;
    private List<Dosage> doslist;

	public UserResponse() {}

	
	
    public List<Dosage> getDoslist() {
		return doslist;
	}



	public void setDoslist(List<Dosage> doslist) {
		this.doslist = doslist;
	}



	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(int firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getRecvInst() {
        return recvInst;
    }

    public void setRecvInst(String recvInst) {
        this.recvInst = recvInst;
    }
}