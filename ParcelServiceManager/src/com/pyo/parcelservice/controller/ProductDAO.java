package com.pyo.parcelservice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pyo.parcelservice.model.ProductVO;
import com.pyo.parcelservice.util.DBUtility;

public class ProductDAO {

    private static final String PRODUCT_SELECT = "SELECT * FROM PRODUCT";
    private static final String PRODUCT_INSERT = "INSERT INTO PRODUCT (CODE, NAME, PRICE, STOCK) VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?)";
    private static final String PRODUCT_UPDATE = "UPDATE PRODUCT SET PRICE = ?, STOCK = ? WHERE NAME = ?";
    private static final String PRODUCT_DELETE = "DELETE FROM PRODUCT WHERE NAME = ?";
    private static final String PRODUCT_NAME_CHECK = "SELECT COUNT(*) AS COUNT FROM PRODUCT WHERE NAME = ?";

    // 상품정보 출력
    public static ArrayList<ProductVO> productSelect() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ProductVO> productList = new ArrayList<ProductVO>();

        con = DBUtility.dbCon();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(PRODUCT_SELECT);

            while (rs.next()) {
                String code = rs.getString("CODE");
                String name = rs.getString("NAME");
                int price = rs.getInt("PRICE");
                int stock = rs.getInt("STOCK");

                ProductVO product = new ProductVO(code, name, price, stock);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, stmt, rs);
        }

        return productList;
    }

    // 상품정보 입력
    public static boolean productInsert(ProductVO pvo) {
        boolean successFlag = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        con = DBUtility.dbCon();

        try {
            // 중복 상품명 체크
            if (isProductNameExists(pvo.getName())) {
                System.out.println("이미 존재하는 상품명입니다.");
                return false;
            }

            pstmt = con.prepareStatement(PRODUCT_INSERT);
            pstmt.setString(1, pvo.getName());
            pstmt.setInt(2, pvo.getPrice());
            pstmt.setInt(3, pvo.getStock());

            int result = pstmt.executeUpdate();
            successFlag = (result != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }
        return successFlag;
    }

    // 상품명 중복 체크
    public static boolean isProductNameExists(String name) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PRODUCT_NAME_CHECK);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt("COUNT") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }
        return exists;
    }

    // 상품정보 수정
    public boolean updateProduct(ProductVO pvo) {
        boolean successFlag = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(PRODUCT_UPDATE);
            pstmt.setInt(1, pvo.getPrice());
            pstmt.setInt(2, pvo.getStock());
            pstmt.setString(3, pvo.getName());

            int result = pstmt.executeUpdate();
            successFlag = (result != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.dbClose(con, pstmt);
        }
        return successFlag;
    }

    // 상품정보 삭제
    public boolean deleteProduct(String name) {
        boolean successFlag = false;
        Connection con = null;
        PreparedStatement pstmt = null;

        con = DBUtility.dbCon();

        try {
            pstmt = con.prepareStatement(PRODUCT_DELETE);
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
