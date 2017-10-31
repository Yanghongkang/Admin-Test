package com.tuberculosis.entity.data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_assessment")
public class Assessment extends DataCommon {
	private BigDecimal anxious;
	private BigDecimal depressed;
	private BigDecimal sleep;
	private BigDecimal nutrition;

	public BigDecimal getAnxious() {
		return anxious;
	}

	public BigDecimal getDepressed() {
		return depressed;
	}

	public BigDecimal getSleep() {
		return sleep;
	}

	public BigDecimal getNutrition() {
		return nutrition;
	}

	public void setAnxious(BigDecimal anxious) {
		this.anxious = anxious;
	}

	public void setDepressed(BigDecimal depressed) {
		this.depressed = depressed;
	}

	public void setSleep(BigDecimal sleep) {
		this.sleep = sleep;
	}

	public void setNutrition(BigDecimal nutrition) {
		this.nutrition = nutrition;
	}

}
