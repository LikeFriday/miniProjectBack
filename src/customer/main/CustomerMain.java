package customer.main;

import dbconnection.MyDBConnection;

import java.util.Scanner;

public class CustomerMain {
    public static void main(String[] args) {
        //DB 연결
        MyDBConnection.getConnection();

        Scanner scanner = new Scanner(System.in);

        //메뉴 열기
        CustomerMenu.Menu(scanner);

    }
}