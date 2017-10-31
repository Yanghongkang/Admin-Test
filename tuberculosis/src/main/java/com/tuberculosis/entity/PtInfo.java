package com.tuberculosis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_pt_info")
public class PtInfo extends IdEntity {

    private String name;
    private int idType;
    private String idCode;
    private int sex;
    private Date birthDate;
    private String currAdds;
    private String contactNum;
    private String notes;
    private Date deathDate;
    private String deathReason;
    private String hospitalNo;
    private String city;
    private String department;

	public PtInfo() {
	}

	public PtInfo(Long id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getBirthDate() {
        return birthDate;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCurrAdds() {
        return currAdds;
    }

    public void setCurrAdds(String currAdds) {
        this.currAdds = currAdds;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public String getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(String deathReason) {
        this.deathReason = deathReason;
    }

    public String getHospitalNo() {
        return hospitalNo;
    }

    public void setHospitalNo(String hospitalNo) {
        this.hospitalNo = hospitalNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}