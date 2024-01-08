package com.jbk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SupplierModel {

	@Min(1)
	private long supplierId;
	
	@NotBlank(message="Invalid supplier name")
	@Pattern(regexp = "^[a-zA-Z -]{3,20}$", message = "Supplier name must be alphabetics and between 3 to 20 characters.")
	private String suppliername;
	
	@NotBlank(message="Invalid city name")
	@Pattern(regexp = "^[a-zA-Z -]{3,20}$", message = "supplier city name must be alphabetics and between 3 to 20 characters.")
	private String city;
	
	@Min(100000)
	@Max(999999)
	private int postalCode;
	
	@NotBlank(message="Invalid country name")
	@Pattern(regexp = "^[a-zA-Z -]{3,20}$", message = "supplier country name must be only alphabetics and between 3 to 20 characters.")
	private String country;
	
	@Pattern(regexp = "^[1-9][0-9]{9}$", message = "supplier mobile number must have 10-15 digit numerical number")
	private String mobileNo;

	public SupplierModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierModel(long supplierId, String suppliername, String city, int postalCode, String country,
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
