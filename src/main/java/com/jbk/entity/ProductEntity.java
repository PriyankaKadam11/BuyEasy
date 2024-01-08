package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class ProductEntity {

	@Id
	@Column(name="Product_Id")
	private long productId;
	
	@Column(name = "Product_Name" , nullable=false , unique=true)
	private String productname;
	
	@OneToOne
	private SupplierEntity supplier;
	
	@OneToOne
	private CategoryEntity category;
	
	@Column(name = "Product_qty" , nullable=false)
	private int productQty;
	
	@Column(name = "Product_price" , nullable=false)
	private double productprice;
	
	@Column(name = "Delivery_charges" , nullable=false)
	private int deliverycharges;

	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductEntity(long productId, String productname, SupplierEntity supplier, CategoryEntity category,
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

	public SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
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
