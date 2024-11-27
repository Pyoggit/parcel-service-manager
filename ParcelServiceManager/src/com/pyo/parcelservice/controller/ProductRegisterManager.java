package com.pyo.parcelservice.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.parcelservice.model.ProductVO;

public class ProductRegisterManager {
    public static Scanner sc = new Scanner(System.in);

    // 상품정보 출력
    public void selectManager() {
        ProductDAO productDao = new ProductDAO();
        ArrayList<ProductVO> productList = productDao.productSelect();

        if (productList == null || productList.isEmpty()) {
            System.out.println("상품 정보가 존재하지 않습니다");
            return;
        }
        printProductList(productList);
    }

    // 상품정보 입력
    public void insertManager() throws SQLException {
        ProductDAO productDao = new ProductDAO();

        System.out.println("상품 정보 입력");
        System.out.print("상품명 >> ");
        String name = sc.nextLine().trim();

        System.out.print("가격 >> ");
        double price = Double.parseDouble(sc.nextLine().trim());

        System.out.print("재고 수량 >> ");
        int stock = Integer.parseInt(sc.nextLine().trim());

        ProductVO productVO = new ProductVO(name, name, stock, stock);
        boolean successFlag = productDao.productInsert(productVO);

        if (!successFlag) {
            System.out.println("입력처리 실패");
            return;
        }

        System.out.println();
        System.out.println("등록된 상품 정보");
        System.out.println(productVO.toString());
    }

    // 상품정보 수정
    public void updateManager() throws SQLException {
        ProductDAO productDao = new ProductDAO();

        System.out.print("수정할 상품명을 입력하세요: ");
        String name = sc.nextLine().trim();

        System.out.print("새로운 가격 >> ");
        int price = Integer.parseInt(sc.nextLine().trim());

        System.out.print("새로운 재고 수량 >> ");
        int stock = Integer.parseInt(sc.nextLine().trim());

        ProductVO productVO = new ProductVO(name, price, stock);
        boolean successFlag = productDao.updateProduct(productVO);

        if (successFlag) {
            System.out.println("수정처리 성공");
        } else {
            System.out.println("수정처리 실패");
        }
    }

    // 상품정보 삭제
    public void deleteManager() throws SQLException {
        ProductDAO productDao = new ProductDAO();

        System.out.print("삭제할 상품명을 입력하세요: ");
        String name = sc.nextLine().trim();
        System.out.print("정말 삭제하시겠습니까? (Y/N): ");
        String confirm = sc.nextLine().trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("삭제 작업이 취소되었습니다.");
            return;
        }

        boolean successFlag = productDao.deleteProduct(name);
        if (successFlag) {
            System.out.println("삭제처리 성공");
        } else {
            System.out.println("삭제처리 실패");
        }
    }

    // 전체 상품 리스트 출력
    public void printProductList(ArrayList<ProductVO> productList) {
        System.out.println("===============================================");
        System.out.printf("%-10s %-20s %-10s %-10s%n", "상품코드", "상품명", "가격", "재고 수량");
        System.out.println("===============================================");
        for (ProductVO product : productList) {
        	System.out.printf("%-10s %-20s %-10d %-10d%n", product.getCode(), product.getName(), product.getPrice(), product.getStock());
        }
        System.out.println("===============================================");
    }
}
