package com.pyo.parcelservice;

import java.sql.SQLException;
import java.util.Scanner;

import com.pyo.parcelservice.controller.CustomerRegisterManager;
import com.pyo.parcelservice.view.CUSTOMER_CHOICE;
import com.pyo.parcelservice.view.MENU_CHOICE;
import com.pyo.parcelservice.view.MenuViewer;

public class ParcelMain {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int no;
        boolean exitFlag = false;
        while (!exitFlag) {
            try {
                MenuViewer.mainMenuView();
                no = Integer.parseInt(sc.nextLine());
                switch (no) {
                    case MENU_CHOICE.CUSTOMER:
                        customerMenu();
                        break;
                    case MENU_CHOICE.PRODUCT:
                        productMenu();
                        break;
                    case MENU_CHOICE.ORDERS:
                        ordersMenu();
                        break;
                    case MENU_CHOICE.EXIT:
                        System.out.println("프로그램을 종료합니다.");
                        exitFlag = true;
                        sc.close();
                        break;
                    default:
                        System.out.println("해당되는 메뉴 번호만 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n입력에 오류가 있습니다. 숫자만 입력하세요.\n");
            }
        } // end of line

    }

    // 회원 정보 메뉴
    private static void customerMenu() {
        int no;
        CustomerRegisterManager crm = new CustomerRegisterManager();

        try {
            MenuViewer.customerMenuView();
            no = Integer.parseInt(sc.nextLine());
            switch (no) {
                case CUSTOMER_CHOICE.LIST:
                    System.out.println("");
                    crm.selectManager();
                    break;
                case CUSTOMER_CHOICE.INSERT:
                    System.out.println("");
                    crm.insertManager();
                    break;
                case CUSTOMER_CHOICE.UPDATE:
                    System.out.println("");
                    crm.updateManager();
                    break;
                case CUSTOMER_CHOICE.DELETE:
                    System.out.println("");
                    crm.deleteManager();
                    break;
                case CUSTOMER_CHOICE.MAIN:
                    return;
                default:
                    System.out.println("해당되는 메뉴 번호만 입력하세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("입력에 오류가 있습니다. 숫자만 입력하세요.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 상품 정보 메뉴 (추가 구현 필요)
    private static void productMenu() {
        System.out.println("상품 메뉴 기능은 아직 구현되지 않았습니다.");
    }

    // 주문 정보 메뉴 (추가 구현 필요)
    private static void ordersMenu() {
        System.out.println("주문 메뉴 기능은 아직 구현되지 않았습니다.");
    }
}