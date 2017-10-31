package com.tuberculosis.entity;

public class DosageVal {
	private String min;

	private String max;
	private Integer value;

	public DosageVal() {
		super();
	}

	public String getMin() {
		return min;
	}

	public String getMax() {
		return max;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
