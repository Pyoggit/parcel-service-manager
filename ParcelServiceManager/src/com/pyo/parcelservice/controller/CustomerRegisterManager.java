package com.pyo.parcelservice.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pyo.parcelservice.model.CustomerVO;

public class CustomerRegisterManager {
    public static Scanner sc = new Scanner(System.in);

    // 회원정보출력
    public void selectManager() {
        CustomerDAO custdao = new CustomerDAO();
        ArrayList<CustomerVO> customerList = custdao.customerSelect();

        if (customerList == null || customerList.isEmpty()) {
            System.out.println("회원정보가 존재하지 않습니다");
            return;
        }
        printCustomerList(customerList);
    }

    // 회원정보입력
    public void insertManager() throws SQLException {
        CustomerDAO customerDao = new CustomerDAO();
        CustomerVO cvo = new CustomerVO();

        System.out.println("회원 정보 입력");
        System.out.print("성명 >> ");
        String name = sc.nextLine();

        String id = null;
        do {
            System.out.print("아이디(8자 이상 12자 이내) : ");
            id = sc.nextLine().trim();
            if (id.length() >= 8 && id.length() <= 12) {
                cvo.setId(id);
                boolean idCheck = customerDao.customerIdCheck(cvo);
                if (!idCheck) {
                    System.out.println("사용 가능한 아이디입니다!");
                    break;
                }
                System.out.println("중복된 아이디입니다. 다시 입력하세요");
            } else {
                System.out.println("아이디는 8자 이상 12자 이내로 입력해야 합니다.");
            }
        } while (true);

        System.out.print("비밀번호(12자 이내) : ");
        String pwd = sc.nextLine().trim();

        System.out.print("생년월일(8자리: 19991111) : ");
        String birth = sc.nextLine().trim();

        System.out.print("전화번호(ex)010-1234-1234) : ");
        String phone = sc.nextLine().trim();

        System.out.print("주소 : ");
        String address = sc.nextLine().trim();

        System.out.print("이메일 : ");
        String email = sc.nextLine().trim();

        CustomerVO customerVO = new CustomerVO(null, name, id, pwd, birth, phone, address, email, null);
        boolean successFlag = customerDao.customerInsert(customerVO);

        if (!successFlag) {
            System.out.println("입력처리 실패");
            return;
        }

        System.out.println();
        System.out.println("등록된 회원 정보");
        System.out.println(customerVO.toString());
    }

    // 회원정보수정
    public void updateManager() throws SQLException {
        CustomerDAO customerDao = new CustomerDAO();
        CustomerVO cvo = new CustomerVO();
        
        System.out.print("수정할 회원의 아이디를 입력하세요: ");
        String id = sc.nextLine().trim();
        cvo.setId(id);
        if (!customerDao.customerIdCheck(cvo)) {
            System.out.println("해당 아이디가 존재하지 않습니다.");
            return;
        }

        System.out.print("새로운 이름을 입력하세요: ");
        String name = sc.nextLine().trim();

        System.out.print("새로운 비밀번호(12자 이내) : ");
        String pwd = sc.nextLine().trim();

        String birth;
        do {
            System.out.print("새로운 생년월일(8자리: 19991111) : ");
            birth = sc.nextLine().trim();
            if (birth.length() == 8) {
                break;
            }
            System.out.println("생년월일은 8자리로 입력해야 합니다.");
        } while (true);

        System.out.print("새로운 전화번호(ex)010-1234-1234) : ");
        String phone = sc.nextLine().trim();

        System.out.print("새로운 주소 : ");
        String address = sc.nextLine().trim();

        System.out.print("새로운 이메일 : ");
        String email = sc.nextLine().trim();

        CustomerVO customerVO = new CustomerVO(null, name, id, pwd, birth, phone, address, email, null);
        boolean successFlag = customerDao.updateCustomer(customerVO);

        if (successFlag) {
            System.out.println("수정처리 성공");
        } else {
            System.out.println("수정처리 실패");
        }
    }
    
    // 회원정보삭제
    public void deleteManager() throws SQLException {
        CustomerDAO customerDao = new CustomerDAO();
        
        System.out.print("삭제할 회원의 아이디를 입력하세요: ");
        String id = sc.nextLine().trim();
        System.out.print("정말 삭제하시겠습니까? (Y/N): ");
        String confirm = sc.nextLine().trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("삭제 작업이 취소되었습니다.");
            return;
        }

        if (!customerDao.customerIdCheck(new CustomerVO(null, null, id, null, null, null, null, null, null))) {
            System.out.println("해당 아이디가 존재하지 않습니다.");
            return;
        }

        boolean successFlag = customerDao.deleteCustomer(id);
        if (successFlag) {
            System.out.println("삭제처리 성공");
        } else {
            System.out.println("삭제처리 실패");
        }
    }

    // 전체회원 리스트 출력
    public void printCustomerList(ArrayList<CustomerVO> customerList) {
        System.out.println("====================================================================================");
        System.out.printf("%-8s %-15s %-12s %-15s %-10s %-20s%n", "CODE", "NAME", "ID", "PHONE", "BIRTH", "EMAIL");
        System.out.println("====================================================================================");
        for (CustomerVO cv : customerList) {
            System.out.printf("%-8s %-15s %-12s %-15s %-10s %-20s%n",
                    cv.getCode(), cv.getName(), cv.getId(), cv.getPhone(), cv.getBirth(), cv.getEmail());
        }
        System.out.println("====================================================================================");
        sc.close();
    }
    
}
