package com.pyo.parcelservice.model;

public class DeliveryVO {
	private String name; //배송업체명,FK
	private int cost;	 //배송비
	
	public DeliveryVO() {}

	public DeliveryVO(String name, int cost) {
		super();
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "[배송업체명=" + name + ", 배송비=" + cost + "]";
	}

}
