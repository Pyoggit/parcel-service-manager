package com.pyo.parcelservice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pyo.parcelservice.model.DeliveryVO;
import com.pyo.parcelservice.util.DBUtility;

public class DeliveryDAO {

    private static final String DELIVERY_SELECT = "SELECT * FROM DELIVERY";
    private static final String DELIVERY_INSERT = "INSERT INTO DELIVERY (NAME, COST) VALUES (?, ?)";
    private static final String DELIVERY_UPDATE = "UPDATE DELIVERY SET COST = ? WHERE NAME = ?";
    private static final String DELIVERY_DELETE = "DELETE FROM DELIVERY WHERE NAME = ?";

    // 배송업체 정보 출력
    public static ArrayList<DeliveryVO> deliverySelect() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<DeliveryVO> deliveryList = new ArrayList<DeliveryVO>();

        con = DBUtility.dbCon();

        try {
            pstmt = con.prepareStatement(DELIVERY_SELECT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("NAME");
                int cost = rs.getInt("COST");

                DeliveryVO delivery = new DeliveryVO(name, cost);
                deliveryList.add(delivery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }

        return deliveryList;
    }

    // 배송업체 정보 입력
    public static boolean deliveryInsert(DeliveryVO dvo) {
        boolean successFlag = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        con = DBUtility.dbCon();

        try {
            pstmt = con.prepareStatement(DELIVERY_INSERT);
            pstmt.setString(1, dvo.getName());
            pstmt.setDouble(2, dvo.getCost());

            int result = pstmt.executeUpdate();
            successFlag = (result != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }
        return successFlag;
    }

    // 배송업체 정보 수정
    public boolean updateDelivery(DeliveryVO dvo) {
        boolean successFlag = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        con = DBUtility.dbCon();

        try {
            pstmt = con.prepareStatement(DELIVERY_UPDATE);
            pstmt.setDouble(1, dvo.getCost());
            pstmt.setString(2, dvo.getName());

            int result = pstmt.executeUpdate();
            successFlag = (result != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }
        return successFlag;
    }

    // 배송업체 정보 삭제
    public boolean deleteDelivery(String name) {
        boolean successFlag = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        con = DBUtility.dbCon();

        try {
            pstmt = con.prepareStatement(DELIVERY_DELETE);
            pstmt.setString(1, name);

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
