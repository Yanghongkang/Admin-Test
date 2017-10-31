package com.tuberculosis.entity.json;

import com.tuberculosis.entity.Hospital;

import java.util.List;

/**
 * @author Li Shaoqing
 */
public class HospitalResponse extends ValidatorError{

	private List<Hospital> hospitals;

	public HospitalResponse() {}

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}