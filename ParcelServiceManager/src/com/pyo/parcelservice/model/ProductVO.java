package com.pyo.parcelservice.model;

public class ProductVO {
	private String code;	//상품코드,FK
	private String name;	//상품명,UK
	private int price;		//상품가격
	private int stock;		//재고
	
	public ProductVO() {}
	
	public ProductVO(String code, String name, int price, int stock) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public ProductVO(String name, int price, int stock) {
		super();
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "[상품명=" + name + ", 상품가격=" + price + ", 재고=" + stock + "]";
	}
	

}
