package com.jbk.model;

public class ProductModel {

	private long productId;
	private String productname;
	private SupplierModel supplier;
	private CategoryModel category;
	private int productQty;
	private double productprice;
	private int deliverycharges;

	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModel(long productId, String productname, SupplierModel supplier, CategoryModel category,
			int productQty, double productprice, int deliverycharges) {
		super();
		this.productId = productId;
		this.productname = productname;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productprice = productprice;
		this.deliverycharges = deliverycharges;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
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

}
