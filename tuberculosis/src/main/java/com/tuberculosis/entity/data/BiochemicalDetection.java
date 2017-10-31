package com.tuberculosis.entity.data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_biochemical_detection")
public class BiochemicalDetection extends DataCommon{
	 	private BigDecimal xqj;
	    private BigDecimal xqm;	
	    private BigDecimal glz;	
	    private BigDecimal zfs;	
	    private BigDecimal ls;	
	    private int xdt;
		public BigDecimal getXqj() {
			return xqj;
		}
		public BigDecimal getXqm() {
			return xqm;
		}
		public BigDecimal getGlz() {
			return glz;
		}
		public BigDecimal getZfs() {
			return zfs;
		}
		public BigDecimal getLs() {
			return ls;
		}
		public int getXdt() {
			return xdt;
		}
		public void setXqj(BigDecimal xqj) {
			this.xqj = xqj;
		}
		public void setXqm(BigDecimal xqm) {
			this.xqm = xqm;
		}
		public void setGlz(BigDecimal glz) {
			this.glz = glz;
		}
		public void setZfs(BigDecimal zfs) {
			this.zfs = zfs;
		}
		public void setLs(BigDecimal ls) {
			this.ls = ls;
		}
		public void setXdt(int xdt) {
			this.xdt = xdt;
		}
		public BiochemicalDetection() {
			super();
		}
	    
	    
}
