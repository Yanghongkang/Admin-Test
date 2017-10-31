package com.tuberculosis.entity.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuberculosis.entity.data.*;

import java.util.Date;
import java.util.List;

/**
 * @author Li Shaoqing
 * 用户
 */
public class PtInfoResponse extends ValidatorError{

    private Long id;
    private String name;
    private String idCode;
    private int sex;
    private int idType;
    private Date birthDate;
    private String currAdds;
    private String contactNum;
    private String notes;
    private int tbDiagnosis;
    private String otherResult;
    private String currTherapy;
    private String adjReason;
    private String therapy;
    private int therapyStatus;
    private String stopReason;
    private Date deathDate;
    private String deathReason;
    private String hospitalNo;
    private String city;
    private String department;
    private List<DrugScpt> drugScptList;
    private List<GeneXpert> geneXpertList;
    private List<Hain> hainList;
    private List<GeneChip> geneChipList;
    private List<TbDiagnosis> tbDiagnosisList;
   
    private int isDosage;

	public int getIsDosage() {
		return isDosage;
	}

	public void setIsDosage(int isDosage) {
		this.isDosage = isDosage;
	}

	public PtInfoResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCurrAdds() {
        return currAdds;
    }

    public void setCurrAdds(String currAdds) {
        this.currAdds = currAdds;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public int getTherapyStatus() {
        return therapyStatus;
    }

    public void setTherapyStatus(int therapyStatus) {
        this.therapyStatus = therapyStatus;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public int getTbDiagnosis() {
        return tbDiagnosis;
    }

    public void setTbDiagnosis(int tbDiagnosis) {
        this.tbDiagnosis = tbDiagnosis;
    }

    public String getOtherResult() {
        return otherResult;
    }

    public void setOtherResult(String otherResult) {
        this.otherResult = otherResult;
    }

    public String getCurrTherapy() {
        return currTherapy;
    }

    public void setCurrTherapy(String currTherapy) {
        this.currTherapy = currTherapy;
    }

    public String getAdjReason() {
        return adjReason;
    }

    public void setAdjReason(String adjReason) {
        this.adjReason = adjReason;
    }

    public String getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(String deathReason) {
        this.deathReason = deathReason;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public List<DrugScpt> getDrugScptList() {
        return drugScptList;
    }

    public void setDrugScptList(List<DrugScpt> drugScptList) {
        this.drugScptList = drugScptList;
    }

    public List<GeneXpert> getGeneXpertList() {
        return geneXpertList;
    }

    public void setGeneXpertList(List<GeneXpert> geneXpertList) {
        this.geneXpertList = geneXpertList;
    }

    public List<Hain> getHainList() {
        return hainList;
    }

    public void setHainList(List<Hain> hainList) {
        this.hainList = hainList;
    }

    public List<GeneChip> getGeneChipList() {
        return geneChipList;
    }

    public void setGeneChipList(List<GeneChip> geneChipList) {
        this.geneChipList = geneChipList;
    }

    public String getHospitalNo() {
        return hospitalNo;
    }

    public void setHospitalNo(String hospitalNo) {
        this.hospitalNo = hospitalNo;
    }

    public List<TbDiagnosis> getTbDiagnosisList() {
        return tbDiagnosisList;
    }

    public void setTbDiagnosisList(List<TbDiagnosis> tbDiagnosisList) {
        this.tbDiagnosisList = tbDiagnosisList;
    }

    
    
}