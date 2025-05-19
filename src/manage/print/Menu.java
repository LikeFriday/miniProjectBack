package manage.print;

import java.util.Scanner;

public class Menu {
    public static void mainMenu(){
        System.out.println("**** 메인메뉴 ***");
        System.out.println("1. 매니저관리");
        System.out.println("2. 상품관리");
        System.out.println("3. 고객관리");
        System.out.println("0. 로그아웃");
        System.out.print("입력 : ");
    }

    public static void menuAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("****메니저 관리 메뉴***");
        System.out.print("1. 관리자 리스트\n2. 관리자 등록\n3. 관리자 삭제\n4. 관리자 수정\n0. 뒤로가기\n");
        System.out.print("입력 : ");
    }

    public static void updateMenu(){
        System.out.println("1. 관리자 이름 변경");
        System.out.println("2. 관리자 비밀번호 변경");
        System.out.println("3. 관리자 메일 변경");
        System.out.println("4. 관리자 등급 변경");
        System.out.println("0. 취소");
        System.out.print("입력 : ");
    }

}
