package com.online.mobilestore1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mobile1_tbl")
public class Mobile {

	@Id
	@Column(name = "mobile_id", length = 5)
	/* @GeneratedValue(strategy = GenerationType.SEQUENCE) */
	private int mobileId;

	@Column(length = 20)
	private String mobileName;

	@Column(length = 20)
	private String modelNo;

	@Column(length = 20)
	private String company;

	public int getMobileId() {
		return mobileId;
	}

	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Mobile(int mobileId, String mobileName, String modelNo, String company) {
		super();
		this.mobileId = mobileId;
		this.mobileName = mobileName;
		this.modelNo = modelNo;
		this.company = company;
	}

	public Mobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", mobileName=" + mobileName + ",  modelNo=" + modelNo + ", company="
				+ company + "]";
	}

}
