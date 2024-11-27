package com.pyo.parcelservice.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pyo.parcelservice.model.OrdersVO;

public class OrdersDAO {

	private static final String ORDERS_SELECT = "SELECT * FROM ORDERS";
	private static final String ORDERS_INSERT = "INSERT INTO ORDERS (CODE, CID, PNAME, DNAME, AMOUNT, TOTAL) VALUES (ORDERS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String ORDERS_UPDATE = "UPDATE ORDERS SET AMOUNT = ?, TOTAL = ? WHERE CODE = ?";
	private static final String ORDERS_DELETE = "DELETE FROM ORDERS WHERE CODE = ?";
	//private static final String ORDERS_SELECT_ALL = "SELECT O.CODE, C.NAME AS CUSTOMER_NAME, O.PNAME, O.DNAME, O.ODATE, O.AMOUNT, O.TOTAL FROM ORDERS O JOIN CUSTOMER C ON O.CID = C.ID JOIN PRODUCT P ON O.PNAME = P.NAME JOIN DELIVERY D ON O.DNAME = D.NAME ORDER BY O.ODATE DESC";

	// 주문정보 출력
	public static ArrayList<OrdersVO> ordersSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<OrdersVO> ordersList = new ArrayList<OrdersVO>();

		con = DBUtility.dbCon();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(ORDERS_SELECT);

			while (rs.next()) {
				String code = rs.getString("CODE");
				String customerId = rs.getString("CID");
				String productName = rs.getString("PNAME");
				String deliveryName = rs.getString("DNAME");
				Date orderDate = rs.getDate("ODATE");
				int amount = rs.getInt("AMOUNT");
				int total = rs.getInt("TOTAL");

				OrdersVO orders = new OrdersVO(code, customerId, productName, deliveryName, orderDate, amount, total);
				ordersList.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}

		return ordersList;
	}

	// 주문정보 입력
	public static boolean ordersInsert(OrdersVO ovo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(ORDERS_INSERT);
			pstmt.setString(1, ovo.getCid());
			pstmt.setString(2, ovo.getPname());
			pstmt.setString(3, ovo.getDname());
			pstmt.setInt(4, ovo.getAmount());

			// 총 주문 금액 계산 (상품 가격 * 수량)
			int total = calculateTotalPrice(ovo.getPname(), ovo.getAmount());
			pstmt.setInt(5, total);

			int result = pstmt.executeUpdate();
			successFlag = (result != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// 총 주문 금액 계산 함수
	private static int calculateTotalPrice(String pname, int amount) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int price = 0;

		try {
			con = DBUtility.dbCon();
			String query = "SELECT PRICE FROM PRODUCT WHERE NAME = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				price = rs.getInt("PRICE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return price * amount;
	}

	// 주문정보 수정
	public boolean updateOrders(OrdersVO ovo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(ORDERS_UPDATE);
			pstmt.setInt(1, ovo.getAmount());

			// 총 주문 금액 계산 (상품 가격 * 수량)
			int total = calculateTotalPrice(ovo.getPname(), ovo.getAmount());
			pstmt.setInt(2, total);
			pstmt.setString(3, ovo.getCode());

			int result = pstmt.executeUpdate();
			successFlag = (result != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// 주문정보 삭제
	public boolean deleteOrders(String code) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(ORDERS_DELETE);
			pstmt.setString(1, code);

			int result = pstmt.executeUpdate();
			successFlag = (result != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

}
