package com.pyo.parcelservice.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pyo.parcelservice.model.CustomerVO;
import com.pyo.parcelservice.util.DBUtility;

public class CustomerDAO {

	private static final String CUSTOMER_SELECT = "SELECT * FROM CUSTOMER";
	private static final String CUSTOMER_INSERT = "INSERT INTO CUSTOMER (CODE, NAME, ID, PWD, ADDRESS, PHONE, BIRTH, EMAIL, CDATE) VALUES (CUSTOMER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
	private static final String CUSTOMER_ID_CHECK = "SELECT COUNT(*) AS COUNT  FROM CUSTOMER WHERE ID = ?";
	private static final String CUSTOMER_UPDATE = "UPDATE CUSTOMER SET NAME = ?, PWD = ?, ADDRESS = ?, PHONE = ?, BIRTH = ?, EMAIL = ? WHERE ID = ?";
	private static final String CUSTOMER_DELETE = "DELETE FROM CUSTOMER WHERE ID = ?";
	private static final String CUSTOMER_SORT = "SELECT * FROM CUSTOMER ORDER BY CDATE ASC, TO_NUMBER(CODE) ASC";

	// 회원정보출력
	public static ArrayList<CustomerVO> customerSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CustomerVO> customerList = new ArrayList<CustomerVO>();

		con = DBUtility.dbCon();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(CUSTOMER_SELECT);

			if (rs.next()) {
				do {
					String code = rs.getString("CODE");
					String name = rs.getString("NAME");
					String id = rs.getString("ID");
					String pwd = rs.getString("PWD");
					String birth = rs.getString("BIRTH");
					String phone = rs.getString("PHONE");
					String address = rs.getString("ADDRESS");
					String email = rs.getString("EMAIL");
					Date cdate = rs.getDate("CDATE");

					CustomerVO cust = new CustomerVO(code, name, id, pwd, birth, phone, address, email, cdate);
					customerList.add(cust);
				} while (rs.next());
			} else {
				customerList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}

		return customerList;
	}

	// 회원정보입력
	public static boolean customerInsert(CustomerVO cvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(CUSTOMER_INSERT);
			pstmt.setString(1, cvo.getName());
			pstmt.setString(2, cvo.getId());
			pstmt.setString(3, cvo.getPwd());
			pstmt.setString(4, cvo.getAddress());
			pstmt.setString(5, cvo.getPhone());
			pstmt.setString(6, cvo.getBirth());
			pstmt.setString(7, cvo.getEmail());

			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// 중복 회원 아이디 체크
	public boolean customerIdCheck(CustomerVO cvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(CUSTOMER_ID_CHECK);
			pstmt.setString(1, cvo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			} else {
				count = 0;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return (count != 0) ? (true) : (false);

	}

	// 회원정보수정
	public boolean updateCustomer(CustomerVO cvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtility.dbCon();
	        pstmt = con.prepareStatement(CUSTOMER_UPDATE);
	        pstmt.setString(1, cvo.getName());   
	        pstmt.setString(2, cvo.getPwd());   
	        pstmt.setString(3, cvo.getAddress());  
	        pstmt.setString(4, cvo.getPhone());   
	        pstmt.setString(5, cvo.getBirth());     
	        pstmt.setString(6, cvo.getEmail());     
	        pstmt.setString(7, cvo.getId());        
	        
			int result = pstmt.executeUpdate();
			successFlag = (result != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// 회원정보삭제
	public boolean deleteCustomer(String id) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(CUSTOMER_DELETE);
			pstmt.setString(1, id);

			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}
	
	// 가입일자와 회원코드 기준으로 정렬된 회원 목록 조회
	public ArrayList<CustomerVO> customerSortedSelect() {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ArrayList<CustomerVO> customerList = new ArrayList<CustomerVO>();

	    try {
	        con = DBUtility.dbCon();
	        pstmt = con.prepareStatement(CUSTOMER_SORT);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            String code = rs.getString("CODE");
	            String name = rs.getString("NAME");
	            String id = rs.getString("ID");
	            String pwd = rs.getString("PWD");
	            String birth = rs.getString("BIRTH");
	            String phone = rs.getString("PHONE");
	            String address = rs.getString("ADDRESS");
	            String email = rs.getString("EMAIL");
	            Date cdate = rs.getDate("CDATE");

	            CustomerVO cust = new CustomerVO(code, name, id, pwd, birth, phone, address, email, cdate);
	            customerList.add(cust);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.dbClose(con, pstmt, rs);
	    }

	    return customerList;
	}

}