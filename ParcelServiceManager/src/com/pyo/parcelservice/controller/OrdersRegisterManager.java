package com.pyo.parcelservice.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.parcelservice.model.OrdersVO;

public class OrdersRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 주문 정보 출력
	public void selectManager() {
		OrdersDAO ordersDao = new OrdersDAO();
		ArrayList<OrdersVO> ordersList = ordersDao.ordersSelect();

		if (ordersList == null || ordersList.isEmpty()) {
			System.out.println("주문 정보가 존재하지 않습니다");
			return;
		}
		printOrdersList(ordersList);
	}

	// 주문 정보 입력
	public void insertManager() throws SQLException {
		OrdersDAO ordersDao = new OrdersDAO();

		System.out.println("주문 정보 입력");
		System.out.print("회원 아이디 >> ");
		String cid = sc.nextLine().trim();

		System.out.print("상품명 >> ");
		String pname = sc.nextLine().trim();

		System.out.print("배송업체명 >> ");
		String dname = sc.nextLine().trim();

		System.out.print("주문 수량 >> ");
		int amount = Integer.parseInt(sc.nextLine().trim());

		OrdersVO ordersVO = new OrdersVO(cid, pname, dname, amount);
		boolean successFlag = ordersDao.ordersInsert(ordersVO);

		if (!successFlag) {
			System.out.println("입력처리 실패");
			return;
		}

		System.out.println();
		System.out.println("등록된 주문 정보");
		System.out.println(ordersVO.toString());
	}

	// 주문 정보 수정
	public void updateManager() throws SQLException {
		OrdersDAO ordersDao = new OrdersDAO();

		System.out.print("수정할 주문 코드 입력 >> ");
		String code = sc.nextLine().trim();

		System.out.print("새로운 주문 수량 >> ");
		int amount = Integer.parseInt(sc.nextLine().trim());

		OrdersVO ordersVO = new OrdersVO(code, amount);
		boolean successFlag = ordersDao.updateOrders(ordersVO);

		if (successFlag) {
			System.out.println("수정처리 성공");
		} else {
			System.out.println("수정처리 실패");
		}
	}

	// 주문 정보 삭제
	public void deleteManager() throws SQLException {
		OrdersDAO ordersDao = new OrdersDAO();

		System.out.print("삭제할 주문 코드 입력 >> ");
		String code = sc.nextLine().trim();
		System.out.print("정말 삭제하시겠습니까? (Y/N): ");
		String confirm = sc.nextLine().trim();
		if (!confirm.equalsIgnoreCase("Y")) {
			System.out.println("삭제 작업이 취소되었습니다.");
			return;
		}

		boolean successFlag = ordersDao.deleteOrders(code);
		if (successFlag) {
			System.out.println("삭제처리 성공");
		} else {
			System.out.println("삭제처리 실패");
		}
	}

	// 전체 주문 리스트 출력
    public void printOrdersList(ArrayList<OrdersVO> ordersList) {
        System.out.println("=======================================================");
        System.out.printf("%-10s %-15s %-20s %-20s %-10s %-10s %-10s%n", "주문코드", "회원아이디", "상품명", "배송업체명", "주문일자", "주문수량", "총금액");
        System.out.println("=======================================================");
        for (OrdersVO orders : ordersList) {
            System.out.printf("%-10s %-15s %-20s %-20s %-10s %-10d %-10d%n", orders.getCode(), orders.getCid(), orders.getPname(), orders.getDname(), orders.getOdate(), orders.getAmount(), orders.getTotal());
        }
        System.out.println("=======================================================");
    }
}
