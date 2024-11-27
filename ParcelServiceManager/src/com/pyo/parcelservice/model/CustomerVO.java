package com.pyo.parcelservice.model;

import java.sql.Date;

public class CustomerVO {
	private String code;	//회원코드,FK,SEQ
	private String name;	//회원이름
	private String id;		//아이디,UK
	private String pwd;		//비밀번호
	private String birth;	//생년월일
	private String phone;	//전화번호
	private String address;	//주소
	private String email;	//이메일
	private Date cdate;		//가입일자

	public CustomerVO() {
	}

	public CustomerVO(String code, String name, String id, String pwd, String birth, String phone, String address,
			String email, Date cdate) {
		super();
		this.code = code;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.birth = birth;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.cdate = cdate;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "[이름=" + name + ", id=" + id + ", 생년월일=" + birth + ", 전화번호=" + phone + ", 주소="
				+ address + ", email=" + email + "]";
	}

}
