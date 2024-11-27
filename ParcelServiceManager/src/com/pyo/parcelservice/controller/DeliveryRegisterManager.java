package com.pyo.parcelservice.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.parcelservice.model.DeliveryVO;

public class DeliveryRegisterManager {
    public static Scanner sc = new Scanner(System.in);

    // 배송업체 정보 출력
    public void selectManager() {
        DeliveryDAO deliveryDao = new DeliveryDAO();
        ArrayList<DeliveryVO> deliveryList = deliveryDao.deliverySelect();

        if (deliveryList == null || deliveryList.isEmpty()) {
            System.out.println("배송업체 정보가 존재하지 않습니다");
            return;
        }
        printDeliveryList(deliveryList);
    }

    // 배송업체 정보 입력
    public void insertManager() throws SQLException {
        DeliveryDAO deliveryDao = new DeliveryDAO();

        System.out.println("배송업체 정보 입력");
        System.out.print("배송업체명 >> ");
        String name = sc.nextLine().trim();

        System.out.print("배송비 >> ");
        int cost = Integer.parseInt(sc.nextLine().trim());

        DeliveryVO deliveryVO = new DeliveryVO(name, cost);
        boolean successFlag = deliveryDao.deliveryInsert(deliveryVO);

        if (!successFlag) {
            System.out.println("입력처리 실패");
            return;
        }

        System.out.println();
        System.out.println("등록된 배송업체 정보");
        System.out.println(deliveryVO.toString());
    }

    // 배송업체 정보 수정
    public void updateManager() throws SQLException {
        DeliveryDAO deliveryDao = new DeliveryDAO();

        System.out.print("수정할 배송업체명을 입력하세요: ");
        String name = sc.nextLine().trim();

        System.out.print("새로운 배송비 >> ");
        int cost = Integer.parseInt(sc.nextLine().trim());

        DeliveryVO deliveryVO = new DeliveryVO(name, cost);
        boolean successFlag = deliveryDao.updateDelivery(deliveryVO);

        if (successFlag) {
            System.out.println("수정처리 성공");
        } else {
            System.out.println("수정처리 실패");
        }
    }

    // 배송업체 정보 삭제
    public void deleteManager() throws SQLException {
        DeliveryDAO deliveryDao = new DeliveryDAO();

        System.out.print("삭제할 배송업체명을 입력하세요: ");
        String name = sc.nextLine().trim();
        System.out.print("정말 삭제하시겠습니까? (Y/N): ");
        String confirm = sc.nextLine().trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("삭제 작업이 취소되었습니다.");
            return;
        }

        boolean successFlag = deliveryDao.deleteDelivery(name);
        if (successFlag) {
            System.out.println("삭제처리 성공");
        } else {
            System.out.println("삭제처리 실패");
        }
    }

    // 전체 배송업체 리스트 출력
    public void printDeliveryList(ArrayList<DeliveryVO> deliveryList) {
        System.out.println("=========================================");
        System.out.printf("%-20s %-10s%n", "배송업체명", "배송비");
        System.out.println("=========================================");
        for (DeliveryVO delivery : deliveryList) {
        	System.out.printf("%-20s %-10d%n", delivery.getName(), delivery.getCost());
        }
        System.out.println("=========================================");
    }
}
