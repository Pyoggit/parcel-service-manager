package com.pyo.parcelservice;

import java.sql.SQLException;
import java.util.Scanner;

import com.pyo.parcelservice.controller.CustomerRegisterManager;
import com.pyo.parcelservice.controller.DeliveryRegisterManager;
import com.pyo.parcelservice.controller.OrdersRegisterManager;
import com.pyo.parcelservice.controller.ProductRegisterManager;
import com.pyo.parcelservice.view.CUSTOMER_CHOICE;
import com.pyo.parcelservice.view.DELIVERY_CHOICE;
import com.pyo.parcelservice.view.MENU_CHOICE;
import com.pyo.parcelservice.view.MenuViewer;
import com.pyo.parcelservice.view.ORDERS_CHOICE;
import com.pyo.parcelservice.view.PRODUCT_CHOICE;

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
				case MENU_CHOICE.DELIVERY:
					deliveryMenu();
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
			case CUSTOMER_CHOICE.SORT:
				System.out.println("");
				crm.sortManager();
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
		int no;
		ProductRegisterManager prm = new ProductRegisterManager();

		try {
			MenuViewer.productMenuView();
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case PRODUCT_CHOICE.LIST:
				System.out.println("");
				prm.selectManager();
				break;
			case PRODUCT_CHOICE.INSERT:
				System.out.println("");
				prm.insertManager();
				break;
			case PRODUCT_CHOICE.UPDATE:
				System.out.println("");
				prm.updateManager();
				break;
			case PRODUCT_CHOICE.DELETE:
				System.out.println("");
				prm.deleteManager();
				break;
			case PRODUCT_CHOICE.MAIN:
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

	// 회원 정보 메뉴
	private static void deliveryMenu() {
		int no;
		DeliveryRegisterManager drm = new DeliveryRegisterManager();

		try {
			MenuViewer.deliveryMenuView();
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case DELIVERY_CHOICE.LIST:
				System.out.println("");
				drm.selectManager();
				break;
			case DELIVERY_CHOICE.INSERT:
				System.out.println("");
				drm.insertManager();
				break;
			case DELIVERY_CHOICE.UPDATE:
				System.out.println("");
				drm.updateManager();
				break;
			case DELIVERY_CHOICE.DELETE:
				System.out.println("");
				drm.deleteManager();
				break;
			case DELIVERY_CHOICE.MAIN:
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

	// 주문 정보 메뉴 (추가 구현 필요)
	private static void ordersMenu() {
		int no;
		OrdersRegisterManager orm = new OrdersRegisterManager();

		try {
			MenuViewer.ordersMenuView();
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case ORDERS_CHOICE.LIST:
				System.out.println("");
				orm.selectManager();
				break;
			case ORDERS_CHOICE.INSERT:
				System.out.println("");
				orm.insertManager();
				break;
			case ORDERS_CHOICE.UPDATE:
				System.out.println("");
				orm.updateManager();
				break;
			case ORDERS_CHOICE.DELETE:
				System.out.println("");
				orm.deleteManager();
				break;
			case ORDERS_CHOICE.MAIN:
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
}