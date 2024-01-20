package com.jbk.model;

public class ProductModelReport {

	private String productname;
	private int productQty;
	private double productprice;
	private int deliverycharges;

	public ProductModelReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModelReport(String productname, int productQty, double productprice, int deliverycharges) {
		super();
		this.productname = productname;
		this.productQty = productQty;
		this.productprice = productprice;
		this.deliverycharges = deliverycharges;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	public int getDeliverycharges() {
		return deliverycharges;
	}

	public void setDeliverycharges(int deliverycharges) {
		this.deliverycharges = deliverycharges;
	}

	@Override
	public String toString() {
		return "ProductModelReport [productname=" + productname + ", productQty=" + productQty + ", productprice="
				+ productprice + ", deliverycharges=" + deliverycharges + "]";
	}

}
