package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierEntity {

	@Id
	@Column(name="supplier_Id")
	private long supplierId;
	
	@Column(name = "supplier_Name" , nullable=false , unique=true)
	private String suppliername;
	
	@Column(name = "city" , nullable=false)
	private String city;
	
	@Column(name = "postal_Code" , nullable=false)
	private int postalCode;
	
	@Column(name = "country" , nullable=false)
	private String country;
	
	@Column(name = "mobile_No" , nullable=false , unique=true)
	private String mobileNo;

	public SupplierEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierEntity(long supplierId, String suppliername, String city, int postalCode, String country,
			String mobileNo) {
		super();
		this.supplierId = supplierId;
		this.suppliername = suppliername;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.mobileNo = mobileNo;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
