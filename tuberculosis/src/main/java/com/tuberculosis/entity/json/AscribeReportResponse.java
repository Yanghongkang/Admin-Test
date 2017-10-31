package com.tuberculosis.entity.json;

import com.tuberculosis.entity.PtInfo;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class AscribeReportResponse {

    private int death;
    private int inCure;
    private int cure;
    private int lose;
    private int fail;
    private int voluntary;
    private int exclude;
    private int complete;
    private int notTreat;
    private int outer;
    private int total;

    private List<Long> deathPtInfo;
    private List<Long> inCurePtInfo;
    private List<Long> curePtInfo;
    private List<Long> losePtInfo;
    private List<Long> failPtInfo;
    private List<Long> voluntaryPtInfo;
    private List<Long> excludePtInfo;
    private List<Long> completePtInfo;
    private List<Long> notTreatPtInfo;
    private List<Long> outerPtInfo;

	public AscribeReportResponse() {}

    public int getVoluntary() {
        return voluntary;
    }

    public void setVoluntary(int voluntary) {
        this.voluntary = voluntary;
    }

    public int getExclude() {
        return exclude;
    }

    public void setExclude(int exclude) {
        this.exclude = exclude;
    }

    public List<Long> getVoluntaryPtInfo() {
        return voluntaryPtInfo;
    }

    public void setVoluntaryPtInfo(List<Long> voluntaryPtInfo) {
        this.voluntaryPtInfo = voluntaryPtInfo;
    }

    public List<Long> getExcludePtInfo() {
        return excludePtInfo;
    }

    public void setExcludePtInfo(List<Long> excludePtInfo) {
        this.excludePtInfo = excludePtInfo;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getInCure() {
        return inCure;
    }

    public void setInCure(int inCure) {
        this.inCure = inCure;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getCure() {
        return cure;
    }

    public void setCure(int cure) {
        this.cure = cure;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Long> getDeathPtInfo() {
        return deathPtInfo;
    }

    public void setDeathPtInfo(List<Long> deathPtInfo) {
        this.deathPtInfo = deathPtInfo;
    }

    public List<Long> getInCurePtInfo() {
        return inCurePtInfo;
    }

    public void setInCurePtInfo(List<Long> inCurePtInfo) {
        this.inCurePtInfo = inCurePtInfo;
    }

    public List<Long> getCurePtInfo() {
        return curePtInfo;
    }

    public void setCurePtInfo(List<Long> curePtInfo) {
        this.curePtInfo = curePtInfo;
    }

    public List<Long> getLosePtInfo() {
        return losePtInfo;
    }

    public void setLosePtInfo(List<Long> losePtInfo) {
        this.losePtInfo = losePtInfo;
    }

    public List<Long> getFailPtInfo() {
        return failPtInfo;
    }

    public void setFailPtInfo(List<Long> failPtInfo) {
        this.failPtInfo = failPtInfo;
    }

    public List<Long> getCompletePtInfo() {
        return completePtInfo;
    }

    public void setCompletePtInfo(List<Long> completePtInfo) {
        this.completePtInfo = completePtInfo;
    }

    public int getNotTreat() {
        return notTreat;
    }

    public void setNotTreat(int notTreat) {
        this.notTreat = notTreat;
    }

    public List<Long> getNotTreatPtInfo() {
        return notTreatPtInfo;
    }

    public void setNotTreatPtInfo(List<Long> notTreatPtInfo) {
        this.notTreatPtInfo = notTreatPtInfo;
    }


    public int getOuter() {
        return outer;
    }

    public void setOuter(int outer) {
        this.outer = outer;
    }

    public List<Long> getOuterPtInfo() {
        return outerPtInfo;
    }

    public void setOuterPtInfo(List<Long> outerPtInfo) {
        this.outerPtInfo = outerPtInfo;
    }
}