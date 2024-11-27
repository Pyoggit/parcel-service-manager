package com.pyo.parcelservice.model;

import java.sql.Date;

public class OrdersVO {
	private String code;	//주문코드,FK
	private String cid;		//회원아이디,FK
	private String pname;	//상품명,FK
	private String dname;	//배송업체명,FK
	private Date odate;		//주문일자
	private int amount;		//주문 수량
	private int total; 		//총 금액
	
	public OrdersVO() {}

	public OrdersVO(String code, String cid, String pname, String dname, Date odate, int amount, int total) {
		super();
		this.code = code;
		this.cid = cid;
		this.pname = pname;
		this.dname = dname;
		this.odate = odate;
		this.amount = amount;
		this.total = total;
	}

	public OrdersVO(String cid, String pname, String dname, int amount) {
		super();
		this.cid = cid;
		this.pname = pname;
		this.dname = dname;
		this.amount = amount;
	}
	
	public OrdersVO(String code, int amount) {
		super();
		this.code = code;
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Date getOdate() {
		return odate;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "[회원아이디=" + cid + ", 상품명=" + pname + ", 배송업체명=" + dname + ", 주문일자=" + odate
				+ ", 주문수량=" + amount + ", 총금액=" + total + "]";
	}
	
	
	
	

}
